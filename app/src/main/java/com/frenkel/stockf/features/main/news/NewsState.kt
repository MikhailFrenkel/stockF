package com.frenkel.stockf.features.main.news

import androidx.compose.runtime.Immutable
import com.frenkel.stockf.features.common.models.NewsUI
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class NewsState(
    val isLoading: Boolean = true,
    val news: ImmutableList<NewsUI> = persistentListOf(),
    val error: String? = null
)
