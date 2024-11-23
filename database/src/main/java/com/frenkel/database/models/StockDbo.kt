package com.frenkel.database.models

data class StockDbo(
    val id: Long = 0,
    val symbol: String,
    val description: String,
    val currency: CurrencyDbo,
    val price: Double? = null,
    val percentChange: Double? = null,
)

data class CurrencyDbo(
    val code: String,
    val symbol: String? = null,
)
