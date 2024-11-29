package com.frenkel.stockf.features.stock_details

import com.frenkel.stockf.features.stock_details.models.StockInfoUI

data class StockDetailState(
    val isLoading: Boolean = true,
    val stockInfo: StockInfoUI? = null,
    val error: String? =  null
)
