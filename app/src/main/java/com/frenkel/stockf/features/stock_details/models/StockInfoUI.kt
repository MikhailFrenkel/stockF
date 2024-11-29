package com.frenkel.stockf.features.stock_details.models

import com.frenkel.data.models.Currency
import com.frenkel.stockf.features.main.models.DisplayableNumber

data class StockInfoUI(
    val symbol: String,
    val companyName: String,
    val logo: String,
    val currency: Currency,
    val price: DisplayableNumber,
    val percentChange: DisplayableNumber,
    val highPriceOfTheDay: DisplayableNumber,
    val lowPriceOfTheDay: DisplayableNumber,
    val openPriceOfTheDay: DisplayableNumber,
    val previousClosePrice: DisplayableNumber,
    val date: String,
    val country: String,
    val exchange: String,
    val industry: String,
    val ipoDate: String,
    val marketCapitalization: DisplayableNumber,
    val shareOutstanding: DisplayableNumber,
    val webUrl: String
)
