package com.frenkel.data.models

import java.util.Date

data class AggregatesResponseDto(
    val ticker: String,
    val queryCount: Int,
    val resultsCount: Int,
    val adjusted: Boolean,
    val results: List<AggregatesTradeDto>,
    val status: String,
    val requestId: String,
    val count: Int
)

data class AggregatesTradeDto(
    val closePrice: Double,
    val highPrice: Double,
    val lowPrice: Double,
    val openPrice: Double,
    val date: Date,
    val volume: Double,
)