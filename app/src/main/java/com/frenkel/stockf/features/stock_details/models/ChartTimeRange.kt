package com.frenkel.stockf.features.stock_details.models

import androidx.annotation.StringRes
import com.frenkel.stockf.R

enum class ChartTimeRange(
    @StringRes val stringId: Int
) {
    SEVEN_DAYS(R.string.chart_time_range_7d),
    ONE_MONTH(R.string.chart_time_range_1m),
    ONE_YEAR(R.string.chart_time_range_1y),
}