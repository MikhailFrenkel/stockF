package com.frenkel.stockf.features.main.models

import androidx.annotation.DrawableRes

data class StockUI(
    val symbol: String,
    @DrawableRes val symbolIcon: Int,
    val description: String,
    val currencySymbol: String,
    val price: DisplayableNumber? = null,
    val pricePercentChange: DisplayableNumber? = null
)
