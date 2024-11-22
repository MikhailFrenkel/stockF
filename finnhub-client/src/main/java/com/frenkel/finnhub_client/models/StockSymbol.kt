package com.frenkel.finnhub_client.models

import kotlinx.serialization.Serializable

@Serializable
data class StockSymbol(
    val currency: String,
    val description: String,
    val displaySymbol: String,
    val figi: String,
    val isin: String?,
    val mic: String,
    val shareClassFIGI: String,
    val symbol: String,
    val symbol2: String,
    val type: String,
)
