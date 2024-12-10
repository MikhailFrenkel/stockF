package com.frenkel.stockf.features.stock_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.frenkel.stockf.features.common.components.ProgressIndicator
import com.frenkel.stockf.features.stock_details.StockDetailAction
import com.frenkel.stockf.features.stock_details.StockDetailState
import com.frenkel.stockf.features.stock_details.models.ChartTimeRange

@Composable
fun ChartSection(
    state: StockDetailState,
    onAction: (StockDetailAction) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        onAction(StockDetailAction.OnChartTimeRangeChanged(ChartTimeRange.SEVEN_DAYS))
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val chartHeight = 238.dp
        var selectedItem by remember { mutableStateOf(ChartTimeRange.SEVEN_DAYS) }
        val chartData = when (selectedItem) {
            ChartTimeRange.SEVEN_DAYS -> state.sevenDaysChartData
            ChartTimeRange.ONE_MONTH -> state.oneMonthChartData
            ChartTimeRange.ONE_YEAR -> state.oneYearChartData
        }

        ChartHeader(
            items = ChartTimeRange.entries.toList(),
            selectedItem = selectedItem,
            onClick = {
                selectedItem = it
                onAction(StockDetailAction.OnChartTimeRangeChanged(selectedItem))
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (chartData == null) {
            ProgressIndicator(
                modifier = Modifier.height(chartHeight)
            )
        } else {
            Chart(
                data = chartData,
                modifier = Modifier.height(chartHeight)
            )
        }
    }
}