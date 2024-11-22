package com.frenkel.stockf.features.main.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.frenkel.stockf.features.main.models.StockUI
import com.frenkel.ui_kit.ui.theme.*

// light / dark
@Composable
internal fun StockItem(
    item: StockUI
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Greyscale50)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = Greyscale100
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = item.symbol,
                color = Greyscale900,
                fontFamily = Satoshi,
                fontSize = MediumFontSize,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.description,
                color = Greyscale500,
                fontFamily = Satoshi,
                fontSize = SmallFontSize,
                fontWeight = FontWeight.Normal
            )
        }

        Text(
            text = "$200",
            color = Greyscale900,
            fontFamily = Satoshi,
            fontSize = MediumFontSize,
            fontWeight = FontWeight.Bold
        )

//        if (item.price != null) {
//            Text(
//                text = "${item.currencySymbol}${item.price.formatted}",
//                color = Greyscale900,
//                fontFamily = Satoshi,
//                fontSize = MediumFontSize,
//                fontWeight = FontWeight.Bold
//            )
//        }
    }
}