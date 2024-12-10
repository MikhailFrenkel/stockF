package com.frenkel.stockf.features.stock_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.frenkel.stockf.R
import com.frenkel.stockf.features.common.components.Card
import com.frenkel.stockf.features.stock_details.models.StockInfoUI

@Composable
fun OverviewTab(
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
                title = stringResource(R.string.date),
                value = stockInfo.date
            )

            InfoSectionItem(
                title = stringResource(R.string.previous_close),
                value = "${stockInfo.currency.symbolOrCode}${stockInfo.previousClosePrice.formatted}"
            )

            InfoSectionItem(
                title = stringResource(R.string.open),
                value = "${stockInfo.currency.symbolOrCode}${stockInfo.openPriceOfTheDay.formatted}"
            )

            InfoSectionItem(
                title = stringResource(R.string.day_s_ranges),
                value = "${stockInfo.currency.symbolOrCode}${stockInfo.lowPriceOfTheDay.formatted} - " +
                        "${stockInfo.currency.symbolOrCode}${stockInfo.highPriceOfTheDay.formatted}",
                isLastItem = true
            )
        }
    }
}