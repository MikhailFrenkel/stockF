package com.frenkel.stockf.features.stock_details

import androidx.compose.runtime.Immutable
import com.frenkel.stockf.features.stock_details.models.CompanyNewsUI
import com.frenkel.stockf.features.stock_details.models.StockInfoUI

@Immutable
data class StockDetailState(
    val isLoading: Boolean = true,
    val stockInfo: StockInfoUI? = null,
    val companyNews: List<CompanyNewsUI>? = null,
    val error: String? =  null
)
