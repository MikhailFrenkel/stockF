package com.frenkel.stockf.features.stock_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.frenkel.stockf.R
import com.frenkel.stockf.features.common.ErrorMessage
import com.frenkel.stockf.features.common.ProgressIndicator
import com.frenkel.stockf.features.common.TopBar
import com.frenkel.stockf.features.stock_details.components.CompanyInfoBanner
import com.frenkel.stockf.features.stock_details.components.InfoSection
import com.frenkel.stockf.features.stock_details.models.StockInfoUI
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun StockDetailScreen(
    navController: NavController,
    symbol: String,
    viewModel: StockDetailsViewModel = koinViewModel { parametersOf(symbol) },
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = { TopBar { navController.navigateUp() } }
    ) { innerPadding ->
        StockDetailScreen(
            state = state,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        )
    }
}

@Composable
private fun StockDetailScreen(
    state: StockDetailState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        when {
            state.isLoading -> ProgressIndicator()
            state.error != null -> ErrorMessage(state.error)
            state.stockInfo != null -> StockDetailContent(state.stockInfo)
            else -> ErrorMessage(stringResource(R.string.sth_went_wrong))
        }
    }
}

@Composable
private fun StockDetailContent(
    stockInfo: StockInfoUI,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        CompanyInfoBanner(stockInfo)

        Spacer(Modifier.height(12.dp))

        InfoSection(stockInfo)
    }
}