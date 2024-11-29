package com.frenkel.data.models

import java.util.Date

data class CompanyNewsDto(
    val category: String,
    val timestamp: Date,
    val headline: String,
    val id: Int,
    val imageUrl: String,
    val related: String,
    val source: String,
    val summary: String,
    val url: String,
)
