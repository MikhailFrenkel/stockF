package com.frenkel.stockf.features.main.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.frenkel.stockf.R
import com.frenkel.stockf.features.main.models.StockUI
import com.frenkel.ui_kit.ui.theme.HeadingH5
import com.frenkel.ui_kit.ui.theme.titleTextColor
import kotlinx.collections.immutable.ImmutableList

@Composable
fun FavoriteStocks(
    items: ImmutableList<StockUI>,
    onClick: (StockUI) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.favorite_stocks),
            color = titleTextColor(),
            style = HeadingH5,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyRow {
            items(items) {
                FavoriteStockItem(
                    item = it,
                    onClick = { onClick(it) }
                )
            }
        }
    }
}