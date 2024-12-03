package com.frenkel.polygon_client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Timespan {
    @SerialName("second") SECOND,
    @SerialName("minute") MINUTE,
    @SerialName("hour") HOUR,
    @SerialName("day") DAY,
    @SerialName("week") WEEK,
    @SerialName("month") MONTH,
    @SerialName("quarter") QUARTER,
    @SerialName("year") YEAR;

    override fun toString(): String {
        return this.name.lowercase()
    }
}