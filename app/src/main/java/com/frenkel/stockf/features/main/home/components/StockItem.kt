package com.frenkel.stockf.features.main.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.frenkel.stockf.features.main.models.StockUI
import com.frenkel.ui_kit.ui.theme.*

@Composable
internal fun StockItem(
    item: StockUI
) {
    val backgroundColor = if (isSystemInDarkTheme()) Greyscale800 else Greyscale50
    val borderColor = if (isSystemInDarkTheme()) Greyscale700 else Greyscale100
    val textColor = if (isSystemInDarkTheme()) Greyscale50 else Greyscale900
    val descriptionColor = if (isSystemInDarkTheme()) Greyscale500 else Greyscale400

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = borderColor
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(item.symbolIcon),
            contentDescription = item.description,
            modifier = Modifier.size(40.dp),
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = item.symbol,
                color = textColor,
                fontFamily = Satoshi,
                fontSize = MediumFontSize,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.description,
                color = descriptionColor,
                fontFamily = Satoshi,
                fontSize = SmallFontSize,
                fontWeight = FontWeight.Normal
            )
        }

        if (item.price != null) {
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${item.currencySymbol}${item.price.formatted}",
                    color = textColor,
                    fontFamily = Satoshi,
                    fontSize = MediumFontSize,
                    fontWeight = FontWeight.Bold
                )

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