package com.frenkel.polygon_client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AggregatesResponse(
    val ticker: String,
    val queryCount: Int,
    val resultsCount: Int,
    val adjusted: Boolean,
    val results: List<Trade>,
    val status: String,
    @SerialName("request_id") val requestId: String,
    val count: Int
)
