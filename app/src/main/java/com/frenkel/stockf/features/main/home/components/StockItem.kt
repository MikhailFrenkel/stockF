package com.frenkel.stockf.features.main.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.frenkel.stockf.features.common.components.Card
import com.frenkel.stockf.features.common.components.PercentChange
import com.frenkel.stockf.features.main.models.StockUI
import com.frenkel.stockf.utils.toSymbolIcon
import com.frenkel.ui_kit.ui.theme.MediumFontSize
import com.frenkel.ui_kit.ui.theme.Satoshi
import com.frenkel.ui_kit.ui.theme.SmallFontSize
import com.frenkel.ui_kit.ui.theme.descriptionTextColor
import com.frenkel.ui_kit.ui.theme.titleTextColor
import java.util.Locale

@Composable
internal fun StockItem(
    item: StockUI,
    onClick: (StockUI) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .testTag("stock_item_${item.symbol}".lowercase())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(item) }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
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

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = item.symbol,
                    color = titleTextColor(),
                    fontFamily = Satoshi,
                    fontSize = MediumFontSize,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.description,
                    color = descriptionTextColor(),
                    fontFamily = Satoshi,
                    fontSize = SmallFontSize,
                    fontWeight = FontWeight.Normal
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                item.price?.let {
                    Text(
                        text = "${item.currencySymbol}${item.price.formatted}",
                        color = titleTextColor(),
                        fontFamily = Satoshi,
                        fontSize = MediumFontSize,
                        fontWeight = FontWeight.Bold
                    )
                }

                item.pricePercentChange?.let {
                    Spacer(modifier = Modifier.height(4.dp))

                    PercentChange(
                        percentChange = it
                    )
                }
            }
        }
    }
}