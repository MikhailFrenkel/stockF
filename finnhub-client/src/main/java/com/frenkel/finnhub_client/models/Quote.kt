package com.frenkel.finnhub_client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    @SerialName("c") val currentPrice: Double,
    @SerialName("d") val change: Double,
    @SerialName("dp") val percentChange: Double,
    @SerialName("h") val highPriceOfTheDay: Double,
    @SerialName("l") val lowPriceOfTheDay: Double,
    @SerialName("o") val openPriceOfTheDay: Double,
    @SerialName("pc") val previousClosePrice: Double,
    @SerialName("t") val timestamp: Long,
)
