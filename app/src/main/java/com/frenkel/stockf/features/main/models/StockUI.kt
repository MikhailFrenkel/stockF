package com.frenkel.stockf.features.main.models

import kotlinx.serialization.Serializable

@Serializable
data class StockUI(
    val symbol: String,
    val description: String,
    val currencySymbol: String,
    val price: DisplayableNumber? = null,
    val pricePercentChange: DisplayableNumber? = null,
    val imageUrl: String? = null,
)
