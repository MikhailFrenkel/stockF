package com.frenkel.stockf.features.common.models

import java.util.Date

data class NewsUI(
    val category: String,
    val date: Date,
    val headline: String,
    val id: Long,
    val imageUrl: String,
    val related: String,
    val source: String,
    val summary: String,
    val url: String,
)
