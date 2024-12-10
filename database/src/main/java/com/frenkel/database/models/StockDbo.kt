package com.frenkel.database.models

data class StockDbo(
    val symbol: String,
    val description: String,
    val currency: CurrencyDbo,
    val price: Double? = null,
    val percentChange: Double? = null,
    val favorite: Boolean = false,
    val imageUrl: String? = null,
    val trending: Boolean = false,
)

data class CurrencyDbo(
    val code: String,
    val symbol: String? = null,
)
