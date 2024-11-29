package com.frenkel.stockf.features.stock_details.models

import java.util.Date

data class CompanyNewsUI(
    val category: String,
    val date: Date,
    val headline: String,
    val id: Int,
    val imageUrl: String,
    val related: String,
    val source: String,
    val summary: String,
    val url: String,
)
