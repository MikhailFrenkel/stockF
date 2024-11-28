package com.frenkel.finnhub_client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RealTimeTradesResponse(
    val type: String,
    val data: List<Trade>
)

@Serializable
data class Trade(
    @SerialName("s") val symbol: String,
    @SerialName("p") val lastPrice: Double,
    @SerialName("t") val timestamp: Long,
    @SerialName("v") val volume: Double,
    @SerialName("c") val conditionCode: Int?
)
