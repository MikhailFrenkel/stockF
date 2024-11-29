package com.frenkel.data.models

import java.util.Date

data class QuoteDto(
    val currentPrice: Double,
    val change: Double,
    val percentChange: Double,
    val highPriceOfTheDay: Double,
    val lowPriceOfTheDay: Double,
    val openPriceOfTheDay: Double,
    val previousClosePrice: Double,
    val date: Date,
)
