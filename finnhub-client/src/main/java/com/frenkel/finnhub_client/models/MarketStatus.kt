package com.frenkel.finnhub_client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarketStatus(
    val exchange: String,
    val holiday: String,
    val isOpen: Boolean,
    val session: String?,
    @SerialName("t") val timestamp: Long,
    val timezone: String
)
