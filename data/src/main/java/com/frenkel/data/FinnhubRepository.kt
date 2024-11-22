package com.frenkel.data

import com.frenkel.data.models.RequestResult
import com.frenkel.data.models.StockSymbolDto
import com.frenkel.finnhub_client.FinnhubApi
import com.frenkel.finnhub_client.models.StockSymbol
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach

interface FinnhubRepository {
    fun getStocksInfo(symbols: List<String>): Flow<RequestResult<List<StockSymbolDto>>>
}

class FinnhubRepositoryImpl(
    private val finnhubApi: FinnhubApi
) : FinnhubRepository {

    override fun getStocksInfo(symbols: List<String>): Flow<RequestResult<List<StockSymbolDto>>> {
        val apiRequest = flow { emit(finnhubApi.fetchStockSymbols()) }
            .onEach {
                val a = 5
            }
            .map { it.toRequestResult() }
            .map { requestResult ->
                requestResult.map { stockSymbols ->
                    stockSymbols
                        .filter { symbols.contains(it.symbol) }
                        .map { it.toStockSymbolDto() }
                }
            }

        //val start = flowOf<RequestResult<List<StockSymbolDto>>>(RequestResult.InProgress())

        return apiRequest//merge(apiRequest, start)
    }

}