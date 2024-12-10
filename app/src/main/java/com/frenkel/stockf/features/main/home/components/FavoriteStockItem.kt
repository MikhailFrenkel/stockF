package com.frenkel.stockf.features.main.home.components

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.frenkel.stockf.features.common.Card
import com.frenkel.stockf.features.common.PercentChange
import com.frenkel.stockf.features.main.models.StockUI
import com.frenkel.stockf.utils.toSymbolIcon
import com.frenkel.ui_kit.ui.theme.BodyMediumBold
import com.frenkel.ui_kit.ui.theme.BodySmallBold
import com.frenkel.ui_kit.ui.theme.descriptionTextColor
import com.frenkel.ui_kit.ui.theme.titleTextColor

@Composable
fun FavoriteStockItem(
    item: StockUI,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .size(142.dp)
            .padding(vertical = 8.dp)
            .padding(end = 16.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = if (item.imageUrl.isNullOrEmpty()) {
                    item.symbol.toSymbolIcon()
                } else {
                    item.imageUrl
                },
                contentDescription = item.description,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = item.symbol,
                color = titleTextColor(),
                style = BodyMediumBold
            )

            Spacer(modifier = Modifier.height(4.dp))

            if (item.price != null && item.pricePercentChange != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${item.currencySymbol}${item.price.formatted}",
                        color = descriptionTextColor(),
                        style = BodySmallBold,
                        modifier = Modifier.weight(1f)
                    )

                    PercentChange(
                        percentChange = item.pricePercentChange,
                        iconSize = 10.dp,
                        textSize = 10.sp,
                        spacing = 2.dp
                    )
                }
            }
        }
    }
}