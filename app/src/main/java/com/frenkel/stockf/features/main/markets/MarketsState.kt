package com.frenkel.stockf.features.main.markets

import androidx.compose.runtime.Immutable
import com.frenkel.stockf.features.main.markets.models.SymbolLookupUI
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class MarketsState(
    val isLoading: Boolean = false,
    val query: String = "",
    val results: ImmutableList<SymbolLookupUI> = persistentListOf(),
    val error: String? = null
)
