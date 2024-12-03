package com.frenkel.data

import com.frenkel.common.addMonth
import com.frenkel.data.models.AggregatesResponseDto
import com.frenkel.data.models.CompanyNewsDto
import com.frenkel.data.models.CompanyProfile2Dto
import com.frenkel.data.models.QuoteDto
import com.frenkel.data.models.RealTimeTradesDto
import com.frenkel.data.models.RequestResult
import com.frenkel.data.models.StockSymbolDto
import com.frenkel.database.StocksDatabase
import com.frenkel.finnhub_client.FinnhubApi
import com.frenkel.finnhub_client.FinnhubWebSocket
import com.frenkel.polygon_client.PolygonApi
import com.frenkel.polygon_client.models.Timespan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Date

interface StocksRepository {
    fun getStocksInfo(
        symbols: List<String>,
        coroutineScope: CoroutineScope
    ): Flow<RequestResult<List<StockSymbolDto>>>

    suspend fun getCompanyProfile2(symbol: String): RequestResult<CompanyProfile2Dto>
    suspend fun getQuote(symbol: String): RequestResult<QuoteDto>
    suspend fun getCompanyNews(
        symbol: String,
        from: Date? = null,
        to: Date? = null
    ): RequestResult<List<CompanyNewsDto>>

    fun observeRealTimeTrades(symbols: List<String>): Flow<RealTimeTradesDto>

    suspend fun getSymbolAggregates(
        symbol: String,
        multiplier: Int,
        timespan: Timespan,
        from: Date,
        to: Date,
    ): RequestResult<AggregatesResponseDto>
}

class StocksRepositoryImpl(
    private val polygonApi: PolygonApi,
    private val finnhubApi: FinnhubApi,
    private val finnhubWebSocket: FinnhubWebSocket,
    private val db: StocksDatabase
) : StocksRepository {

    override fun getStocksInfo(
        symbols: List<String>,
        coroutineScope: CoroutineScope
    ): Flow<RequestResult<List<StockSymbolDto>>> {
        coroutineScope.launch {
            getStocksInfoFromServer(symbols, coroutineScope).collect()
        }

        return getStocksInfoFromCache()
    }

    override suspend fun getCompanyProfile2(symbol: String): RequestResult<CompanyProfile2Dto> {
        return finnhubApi.fetchCompanyProfile2(symbol)
            .toRequestResult()
            .map { it.toDto() }
    }

    override suspend fun getQuote(symbol: String): RequestResult<QuoteDto> {
        return finnhubApi.fetchQuote(symbol)
            .toRequestResult()
            .map { it.toDto() }
    }

    override suspend fun getCompanyNews(
        symbol: String,
        from: Date?,
        to: Date?
    ): RequestResult<List<CompanyNewsDto>> {
        val fromDate = from ?: Date().addMonth(-1)
        val toDate = to ?: Date()

        return finnhubApi.fetchCompanyNews(symbol, fromDate, toDate)
            .toRequestResult()
            .map { list ->
                list.map { it.toDto() }
            }
    }

    override fun observeRealTimeTrades(symbols: List<String>): Flow<RealTimeTradesDto> {
        return finnhubWebSocket.observeTrades(symbols)
            .map { it.toDto() }
    }

    override suspend fun getSymbolAggregates(
        symbol: String,
        multiplier: Int,
        timespan: Timespan,
        from: Date,
        to: Date
    ): RequestResult<AggregatesResponseDto> {
        return polygonApi.fetchAggregates(
            stocksTicker = symbol,
            multiplier = multiplier,
            timespan = timespan,
            from = from,
            to = to
        ).toRequestResult()
            .map { it.toDto() }
    }

    private fun getStocksInfoFromCache(): Flow<RequestResult<List<StockSymbolDto>>> {
        return db.observeAll()
            .map { dbos ->
                if (dbos.isEmpty()) {
                    RequestResult.InProgress()
                } else {
                    RequestResult.Success(dbos.map { it.toStockSymbolDto() })
                }
            }
            .catch { RequestResult.Error<List<StockSymbolDto>>(error = it) }
    }

    private fun getStocksInfoFromServer(
        symbols: List<String>,
        coroutineScope: CoroutineScope
    ): Flow<RequestResult<List<StockSymbolDto>>> {
        return flow {
            var requestResult = fetchStockSymbols(symbols)
            emit(requestResult)

            requestResult = fetchQuotes(requestResult, coroutineScope)
            emit(requestResult)

            val marketStatus = finnhubApi.fetchMarketStatus().getOrNull()
            if (marketStatus?.isOpen == true) {
                emitAll(
                    observeRealTimeTrades(requestResult)
                )
            }

        }.onEach { result ->
            result.data?.let {
                saveToCache(it)
            }
        }
    }

    private suspend fun fetchStockSymbols(symbols: List<String>): RequestResult<List<StockSymbolDto>> {
        val orderMap = symbols.withIndex().associate { it.value to it.index }

        return finnhubApi.fetchStockSymbols()
            .toRequestResult()
            .map { stockSymbols ->
                stockSymbols
                    .filter { symbols.contains(it.symbol) }
                    .sortedBy { orderMap[it.symbol] ?: Int.MAX_VALUE }
                    .map { it.toStockSymbolDto() }
            }
    }

    private suspend fun fetchQuotes(
        requestResult: RequestResult<List<StockSymbolDto>>,
        coroutineScope: CoroutineScope
    ): RequestResult<List<StockSymbolDto>> {
        if (requestResult.data == null)
            return requestResult

        return coroutineScope.async {
            val quoteRequests = requestResult.data
                ?.map { it.symbol }
                ?.map { async {
                    val response = finnhubApi.fetchQuote(it)
                    it to response.getOrNull()
                } }

            if (quoteRequests != null) {
                val quotes = quoteRequests.awaitAll()

                requestResult.map { stockSymbolDtos ->
                    stockSymbolDtos.map { stockSymbolDto ->
                        val quote = quotes
                            .firstOrNull { it.first == stockSymbolDto.symbol }
                            ?.second

                        if (quote != null) {
                            stockSymbolDto.copy(
                                price = quote.currentPrice,
                                percentChange = quote.percentChange
                            )
                        } else {
                            stockSymbolDto
                        }
                    }
                }
            } else {
                requestResult
            }
        }.await()
    }

    private fun observeRealTimeTrades(
        requestResult: RequestResult<List<StockSymbolDto>>
    ): Flow<RequestResult<List<StockSymbolDto>>> {
        return if (requestResult.data == null) {
            emptyFlow()
        } else {
            val symbols = requestResult.data!!.map { it.symbol }
            observeRealTimeTrades(symbols)
                .map { realTimeTradesResponse ->
                    requestResult.map { stockSymbols ->
                        stockSymbols.map { stockSymbol ->
                            val realTimeTrade = realTimeTradesResponse.data.firstOrNull {
                                it.symbol == stockSymbol.symbol
                            }

                            if (realTimeTrade != null) {
                                stockSymbol.copy(
                                    price = realTimeTrade.lastPrice,
                                )
                            } else {
                                stockSymbol
                            }
                        }
                    }
                }
        }
    }

    private suspend fun saveToCache(data: List<StockSymbolDto>) {
        val dbos = data.map { it.toStockDbo() }
        db.cleanAndInsert(dbos)
    }
}