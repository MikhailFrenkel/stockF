package com.frenkel.data

import com.frenkel.data.models.RequestResult
import com.frenkel.data.models.StockSymbolDto
import com.frenkel.database.StocksDatabase
import com.frenkel.finnhub_client.FinnhubApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

interface FinnhubRepository {
    fun getStocksInfo(
        symbols: List<String>,
        coroutineScope: CoroutineScope
    ): Flow<RequestResult<List<StockSymbolDto>>>
}

class FinnhubRepositoryImpl(
    private val finnhubApi: FinnhubApi,
    private val db: StocksDatabase
) : FinnhubRepository {

    override fun getStocksInfo(
        symbols: List<String>,
        coroutineScope: CoroutineScope
    ): Flow<RequestResult<List<StockSymbolDto>>> {
        coroutineScope.launch {
            getStocksInfoFromServer(symbols, coroutineScope).collect()
        }

        return getStocksInfoFromCache()
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
        return fetchStockSymbols(symbols)
            .map { fetchQuotes(it, coroutineScope) }
            .onEach { result ->
                result.data?.let {
                    saveToCache(it)
                }
            }
    }

    private fun fetchStockSymbols(symbols: List<String>): Flow<RequestResult<List<StockSymbolDto>>> {
        val orderMap = symbols.withIndex().associate { it.value to it.index }

        val apiRequest = flow { emit(finnhubApi.fetchStockSymbols()) }
            .map { it.toRequestResult() }
            .map { requestResult ->
                requestResult.map { stockSymbols ->
                    stockSymbols
                        .filter { symbols.contains(it.symbol) }
                        .sortedBy { orderMap[it.symbol] ?: Int.MAX_VALUE }
                        .map { it.toStockSymbolDto() }
                }
            }

        return apiRequest
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

    private suspend fun saveToCache(data: List<StockSymbolDto>) {
        val dbos = data.map { it.toStockDbo() }

        db.clean()
        db.insert(dbos)
    }
}