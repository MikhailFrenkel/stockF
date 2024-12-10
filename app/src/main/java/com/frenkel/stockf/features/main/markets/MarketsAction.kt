package com.frenkel.stockf.features.main.markets

sealed interface MarketsAction {
    data class OnSearch(val query: String) : MarketsAction
}