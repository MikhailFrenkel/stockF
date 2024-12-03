package com.frenkel.stockf.features.stock_details

import com.frenkel.stockf.features.stock_details.models.ChartTimeRange

sealed interface StockDetailAction {
    data class OnChartTimeRangeChanged(val timeRange: ChartTimeRange) : StockDetailAction
}