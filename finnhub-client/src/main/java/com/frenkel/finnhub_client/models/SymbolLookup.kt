package com.frenkel.finnhub_client.models

import kotlinx.serialization.Serializable

@Serializable
data class SymbolLookup(
    val description: String,
    val displaySymbol: String,
    val symbol: String,
    val type: String
)
