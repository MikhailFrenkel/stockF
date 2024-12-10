package com.frenkel.stockf.features.common

import com.frenkel.data.models.NewsDto
import com.frenkel.stockf.features.common.models.NewsUI
import java.util.Date

internal fun NewsDto.toUI(): NewsUI = NewsUI(
    category = category,
    date = Date(timestamp * 1000),
    headline = headline,
    id = id,
    imageUrl = imageUrl,
    related = related,
    source = source,
    summary = summary,
    url = url
)