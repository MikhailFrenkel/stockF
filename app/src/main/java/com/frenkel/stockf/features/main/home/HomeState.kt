package com.frenkel.stockf.features.main.home

import androidx.compose.runtime.Immutable
import com.frenkel.stockf.features.main.models.StockUI
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class HomeState(
    val isLoading: Boolean = true,
    val stocks: ImmutableList<StockUI> = persistentListOf(),
    val favoriteStocks: ImmutableList<StockUI>? = null,
    val error: String? = null
)
