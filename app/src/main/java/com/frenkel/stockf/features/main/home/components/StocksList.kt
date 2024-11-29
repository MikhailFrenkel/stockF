package com.frenkel.stockf.features.main.home.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.frenkel.stockf.features.main.models.StockUI
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun StocksList(
    items: ImmutableList<StockUI>,
    onItemClick: (StockUI) -> Unit
) {
    LazyColumn {
        items(items) {
            StockItem(it, onItemClick)
        }
    }
}