package com.frenkel.stockf.features.main.news

sealed interface NewsAction {
    data object OnPullToRefresh : NewsAction
}