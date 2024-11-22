package com.frenkel.data.models

data class StockSymbolDto(
    val symbol: String,
    val description: String,
    val currency: Currency,
    val price: Double? = null
)
