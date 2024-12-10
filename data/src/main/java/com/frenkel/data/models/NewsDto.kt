package com.frenkel.data.models

data class NewsDto(
    val id: Long,
    val category: String,
    val timestamp: Long,
    val headline: String,
    val imageUrl: String,
    val related: String,
    val source: String,
    val summary: String,
    val url: String,
)
