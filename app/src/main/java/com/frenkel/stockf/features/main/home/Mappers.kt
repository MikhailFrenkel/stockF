package com.frenkel.stockf.features.main.home

import com.frenkel.data.models.RequestResult
import com.frenkel.data.models.StockSymbolDto
import com.frenkel.stockf.features.main.models.StockUI
import com.frenkel.stockf.features.main.models.toDisplayableNumber

internal fun StockSymbolDto.toStockUI(): StockUI {
    return StockUI(
        symbol = this.symbol,
        description = this.description,
        currencySymbol = this.currency.symbolOrCode,
        price = this.price?.toDisplayableNumber(),
        pricePercentChange = this.percentChange?.toDisplayableNumber(),
        imageUrl = imageUrl
    )
}

internal fun RequestResult<List<StockSymbolDto>>.toHomeState(block: (List<StockSymbolDto>) -> HomeState): HomeState {
    return when (this) {
        is RequestResult.InProgress -> HomeState(isLoading = true)
        is RequestResult.Success -> block(data)
        is RequestResult.Error -> HomeState(isLoading = false, error = error?.message)
    }
}