package com.frenkel.stockf.features.main.models

data class StockUI(
    val symbol: String,
    val description: String,
    val currencySymbol: String,
    val price: DisplayableNumber? = null,
    val priceChange: DisplayableNumber? = null
)
