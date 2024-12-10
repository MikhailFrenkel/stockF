package com.frenkel.stockf.features.main.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.frenkel.stockf.R
import com.frenkel.stockf.features.main.home.HomeState
import com.frenkel.stockf.features.main.models.StockUI
import com.frenkel.ui_kit.ui.theme.HeadingH5
import com.frenkel.ui_kit.ui.theme.titleTextColor

@Composable
internal fun StocksList(
    state: HomeState,
    onItemClick: (StockUI) -> Unit
) {
    LazyColumn {
        state.favoriteStocks?.let {
            item {
                FavoriteStocks(it, onItemClick)
            }
        }

        item {
            Text(
                text = stringResource(R.string.trending_stocks),
                color = titleTextColor(),
                style = HeadingH5,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        items(state.stocks) {
            StockItem(it, onItemClick)
        }
    }
}