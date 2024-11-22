package com.frenkel.stockf.features.main.home

import com.frenkel.stockf.features.main.models.StockUI
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class HomeState(
    val isLoading: Boolean = true,
    val stocks: ImmutableList<StockUI> = persistentListOf(),
    val error: String? = null
)
