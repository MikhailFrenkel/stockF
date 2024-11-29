package com.frenkel.data.models

data class CompanyProfile2Dto(
    val country: String,
    val currency: Currency,
    val exchange: String,
    val finnhubIndustry: String,
    val ipo: String,
    val logo: String,
    val marketCapitalization: Double,
    val name: String,
    val phone: String,
    val shareOutstanding: Double,
    val ticker: String,
    val webUrl: String,
)
