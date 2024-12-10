package com.frenkel.database.models

data class NewsDbo(
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
