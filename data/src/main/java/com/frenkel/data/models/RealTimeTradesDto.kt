package com.frenkel.data.models

data class RealTimeTradesDto(
    val type: String,
    val data: List<TradeDto>
)

data class TradeDto(
    val symbol: String,
    val lastPrice: Double,
    val timestamp: Long,
    val volume: Double,
)
