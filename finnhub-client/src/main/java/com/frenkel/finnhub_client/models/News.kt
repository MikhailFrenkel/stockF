package com.frenkel.finnhub_client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class News(
    val category: String,
    @SerialName("datetime") val timestamp: Long,
    val headline: String,
    val id: Long,
    @SerialName("image") val imageUrl: String,
    val related: String,
    val source: String,
    val summary: String,
    val url: String,
)
