package com.frenkel.finnhub_client.models

import kotlinx.serialization.Serializable

@Serializable
data class SymbolLookupResponse(
    val count: Int,
    val result: List<SymbolLookup>
)
