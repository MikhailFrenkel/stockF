package com.frenkel.stockf.features.stock_details.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.frenkel.stockf.R
import com.frenkel.stockf.features.common.PercentChange
import com.frenkel.stockf.features.stock_details.models.StockInfoUI
import com.frenkel.stockf.utils.toSymbolIcon
import com.frenkel.ui_kit.ui.theme.*

@Composable
internal fun CompanyInfoBanner(
    stockInfo: StockInfoUI,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val imageSize = 44.dp

        if (stockInfo.logo.isNotEmpty()) {
            AsyncImage(
                stockInfo.logo,
                contentDescription = stringResource(R.string.company_logo),
                placeholder = painterResource(R.drawable.ic_unknown_logo),
                modifier = Modifier
                    .size(imageSize)
                    .clip(CircleShape)
            )
        } else {
            Icon(
                painter = painterResource(stockInfo.symbol.toSymbolIcon()),
                contentDescription = stringResource(R.string.company_logo),
                modifier = Modifier.size(imageSize),
                tint = Color.Unspecified
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = stockInfo.symbol,
                color = titleTextColor(),
                style = BodyLargeBold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stockInfo.companyName,
                color = descriptionTextColor(),
                style = BodyMediumRegular
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${stockInfo.currency.symbolOrCode}${stockInfo.price.formatted}",
                color = titleTextColor(),
                style = BodyLargeBold
            )

            Spacer(modifier = Modifier.height(4.dp))

            PercentChange(percentChange = stockInfo.percentChange)
        }
    }
}