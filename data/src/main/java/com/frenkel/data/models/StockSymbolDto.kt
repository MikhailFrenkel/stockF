package com.frenkel.data.models

data class StockSymbolDto(
    val symbol: String,
    val description: String,
    val currency: Currency,
    val price: Double? = null,
    val percentChange: Double? = null,
    val favorite: Boolean = false,
    val imageUrl: String? = null,
    val trending: Boolean = false,
)
