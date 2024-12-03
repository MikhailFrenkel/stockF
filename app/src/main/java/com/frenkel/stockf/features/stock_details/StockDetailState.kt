package com.frenkel.stockf.features.stock_details

import androidx.compose.runtime.Immutable
import com.frenkel.stockf.features.stock_details.models.CompanyNewsUI
import com.frenkel.stockf.features.stock_details.models.StockInfoUI
import java.util.Date

@Immutable
data class StockDetailState(
    val isLoading: Boolean = true,
    val stockInfo: StockInfoUI? = null,
    val companyNews: List<CompanyNewsUI>? = null,
    val sevenDaysChartData: Map<Date, Double>? = null,
    val oneMonthChartData: Map<Date, Double>? = null,
    val oneYearChartData: Map<Date, Double>? = null,
    val error: String? =  null
)
