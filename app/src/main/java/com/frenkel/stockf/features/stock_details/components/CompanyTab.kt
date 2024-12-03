package com.frenkel.stockf.features.stock_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.frenkel.stockf.R
import com.frenkel.stockf.features.common.Card
import com.frenkel.stockf.features.common.HyperlinkText
import com.frenkel.stockf.features.stock_details.models.StockInfoUI
import com.frenkel.ui_kit.ui.theme.BodyMediumMedium
import com.frenkel.ui_kit.ui.theme.titleTextColor

@Composable
fun CompanyTab(
    stockInfo: StockInfoUI,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            InfoSectionItem(
                title = stringResource(R.string.market_capitalization),
                value = "${stockInfo.currency.symbolOrCode}${stockInfo.marketCapitalization.formatted}"
            )

            InfoSectionItem(
                title = stringResource(R.string.share_outstanding),
                value = stockInfo.shareOutstanding.formatted
            )

            InfoSectionItem(
                title = stringResource(R.string.country),
                value = stockInfo.country
            )

            InfoSectionItem(
                title = stringResource(R.string.exchange),
                value = stockInfo.exchange
            )

            InfoSectionItem(
                title = stringResource(R.string.industry),
                value = stockInfo.industry
            )

            InfoSectionItem(
                title = stringResource(R.string.ipo_date),
                value = stockInfo.ipoDate,
                isLastItem = stockInfo.webUrl.isEmpty()
            )

            if (stockInfo.webUrl.isNotEmpty()) {
                HyperlinkText(
                    text = stringResource(R.string.website),
                    url = stockInfo.webUrl,
                    color = titleTextColor(),
                    style = BodyMediumMedium,
                    modifier = Modifier
                        .padding(vertical = 12.dp),
                )
            }
        }
    }
}