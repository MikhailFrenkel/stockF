package com.frenkel.stockf.features.main

import com.frenkel.data.models.RequestResult
import com.frenkel.data.models.StockSymbolDto
import com.frenkel.stockf.features.main.home.HomeState
import com.frenkel.stockf.features.main.models.StockUI
import com.frenkel.stockf.features.main.models.toDisplayableNumber
import kotlinx.collections.immutable.toImmutableList

internal fun StockSymbolDto.toStockUI(): StockUI {
    return StockUI(
        symbol = this.symbol,
        description = this.description,
        currencySymbol = this.currency.symbolOrCode,
        price = this.price?.toDisplayableNumber(),
    )
}

internal fun RequestResult<List<StockSymbolDto>>.toHomeState(): HomeState {
    return when (this) {
        is RequestResult.InProgress -> HomeState(isLoading = true)
        is RequestResult.Success -> HomeState(
            isLoading = false,
            stocks = data.map { it.toStockUI() }.toImmutableList()
        )
        is RequestResult.Error -> HomeState(isLoading = false, error = error?.message)
    }
}

//internal fun RequestResult<List<StockSymbolDto>>.toHomeState(previous: HomeState): HomeState {
//    return when (this) {
//        is RequestResult.InProgress -> HomeState(isLoading = true)
//        is RequestResult.Success -> HomeState(
//            isLoading = false,
//            stocks = data.map { stockSymbolDto ->
//                val previousStockUI = previous.stocks.firstOrNull { it.symbol == stockSymbolDto.symbol }
//                stockSymbolDto.toStockUI(previousStockUI)
//            }
//        )
//    }
//}