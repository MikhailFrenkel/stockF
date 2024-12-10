package com.frenkel.stockf.features.main.markets.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.frenkel.stockf.features.main.markets.models.SymbolLookupUI
import kotlinx.collections.immutable.ImmutableList

@Composable
fun SearchResults(
    results: ImmutableList<SymbolLookupUI>,
    onClick: (SymbolLookupUI) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(results) {
            SearchResult(
                item = it,
                onClick = { onClick(it) },
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}