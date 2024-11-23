package com.frenkel.stockf.features.main

import com.frenkel.data.models.RequestResult
import com.frenkel.data.models.StockSymbolDto
import com.frenkel.stockf.features.main.home.HomeState
import com.frenkel.stockf.features.main.models.StockUI
import com.frenkel.stockf.features.main.models.toDisplayableNumber
import com.frenkel.stockf.utils.toSymbolIcon
import kotlinx.collections.immutable.toImmutableList

internal fun StockSymbolDto.toStockUI(): StockUI {
    return StockUI(
        symbol = this.symbol,
        symbolIcon = this.symbol.toSymbolIcon(),
        description = this.description,
        currencySymbol = this.currency.symbolOrCode,
        price = this.price?.toDisplayableNumber(),
        pricePercentChange = this.percentChange?.toDisplayableNumber()
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