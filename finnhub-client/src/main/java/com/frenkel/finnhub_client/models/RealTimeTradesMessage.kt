package com.frenkel.finnhub_client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RealTimeTradesMessage(
    val type: MessageType,
    val symbol: String
)

@Serializable
enum class MessageType {
    @SerialName("subscribe") SUBSCRIBE,
    @SerialName("unsubscribe") UNSUBSCRIBE,
}
