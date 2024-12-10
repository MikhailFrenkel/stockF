package com.frenkel.database_android.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.frenkel.database.models.NewsDbo

@Entity(tableName = "news")
data class NewsRoomDbo(
    @PrimaryKey val id: Long,
    val category: String,
    val timestamp: Long,
    val headline: String,
    val imageUrl: String,
    val related: String,
    val source: String,
    val summary: String,
    val url: String,
)

internal fun NewsRoomDbo.toDbo(): NewsDbo = NewsDbo(
    id = id,
    category = category,
    timestamp = timestamp,
    headline = headline,
    imageUrl = imageUrl,
    related = related,
    source = source,
    summary = summary,
    url = url
)

internal fun NewsDbo.toRoomDbo(): NewsRoomDbo = NewsRoomDbo(
    id = id,
    category = category,
    timestamp = timestamp,
    headline = headline,
    imageUrl = imageUrl,
    related = related,
    source = source,
    summary = summary,
    url = url
)