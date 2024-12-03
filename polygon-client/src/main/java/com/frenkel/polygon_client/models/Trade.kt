package com.frenkel.polygon_client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trade(
    @SerialName("c") val closePrice: Double,
    @SerialName("h") val highPrice: Double,
    @SerialName("l") val lowPrice: Double,
    @SerialName("o") val openPrice: Double,
    @SerialName("t") val timestamp: Long,
    @SerialName("v") val volume: Double,
)
