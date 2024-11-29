package com.frenkel.finnhub_client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyProfile2(
    val country: String,
    val currency: String,
    val exchange: String,
    val finnhubIndustry: String,
    val ipo: String,
    val logo: String,
    val marketCapitalization: Double,
    val name: String,
    val phone: String,
    val shareOutstanding: Double,
    val ticker: String,
    @SerialName("weburl") val webUrl: String,
)
