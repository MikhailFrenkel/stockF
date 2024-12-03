package com.frenkel.polygon_client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Sort {
    @SerialName("asc") ASC,
    @SerialName("desc") DESC;

    override fun toString(): String {
        return this.name.lowercase()
    }
}