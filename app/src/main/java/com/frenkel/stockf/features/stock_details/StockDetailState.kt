package com.frenkel.stockf.features.stock_details

import androidx.compose.runtime.Immutable
import com.frenkel.stockf.features.common.models.NewsUI
import com.frenkel.stockf.features.stock_details.models.StockInfoUI
import java.util.Date

@Immutable
data class StockDetailState(
    val isLoading: Boolean = true,
    val stockInfo: StockInfoUI? = null,
    val companyNews: List<NewsUI>? = null,
    val sevenDaysChartData: Map<Date, Double>? = null,
    val oneMonthChartData: Map<Date, Double>? = null,
    val oneYearChartData: Map<Date, Double>? = null,
    val isFavorite: Boolean = false,
    val error: String? =  null
)
