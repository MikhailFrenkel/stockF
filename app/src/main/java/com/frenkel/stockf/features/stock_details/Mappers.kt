package com.frenkel.stockf.features.stock_details

import com.frenkel.data.models.CompanyNewsDto
import com.frenkel.stockf.features.stock_details.models.CompanyNewsUI
import com.frenkel.stockf.utils.toRelativeDateString

fun CompanyNewsDto.toUI(): CompanyNewsUI = CompanyNewsUI(
    category = category,
    date = timestamp,
    headline = headline,
    id = id,
    imageUrl = imageUrl,
    related = related,
    source = source,
    summary = summary,
    url = url
)