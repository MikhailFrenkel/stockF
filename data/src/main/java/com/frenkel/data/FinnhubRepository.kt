package com.frenkel.data

import com.frenkel.data.models.RequestResult
import com.frenkel.data.models.StockSymbolDto
import com.frenkel.finnhub_client.FinnhubApi
import com.frenkel.finnhub_client.models.StockSymbol
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

interface FinnhubRepository {
    fun getStocksInfo(
        symbols: List<String>,
        coroutineScope: CoroutineScope
    ): Flow<RequestResult<List<StockSymbolDto>>>
}

class FinnhubRepositoryImpl(
    private val finnhubApi: FinnhubApi
) : FinnhubRepository {

    override fun getStocksInfo(
        symbols: List<String>,
        coroutineScope: CoroutineScope
    ): Flow<RequestResult<List<StockSymbolDto>>> {
        return fetchStockSymbols(symbols)
            .map { fetchQuotes(it, coroutineScope) }
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
}