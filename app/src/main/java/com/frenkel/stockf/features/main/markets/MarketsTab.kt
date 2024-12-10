package com.frenkel.stockf.features.main.markets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.frenkel.stockf.R
import com.frenkel.stockf.Route
import com.frenkel.stockf.features.common.components.ErrorMessage
import com.frenkel.stockf.features.common.components.ProgressIndicator
import com.frenkel.stockf.features.common.components.SearchField
import com.frenkel.stockf.features.main.markets.components.NoResults
import com.frenkel.stockf.features.main.markets.components.SearchResults
import org.koin.androidx.compose.koinViewModel

@Composable
fun MarketsTab(
    navController: NavController,
    viewModel: MarketsViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        SearchField(
            text = query,
            placeholder = stringResource(R.string.search_ticker),
            onTextChanged = { viewModel.onAction(MarketsAction.OnSearch(it)) },
            onClear = { viewModel.onAction(MarketsAction.OnSearch("")) },
        )

        when {
            state.error != null -> ErrorMessage(state.error!!)
            state.isLoading -> ProgressIndicator()
            state.query.isNotBlank() && state.results.isEmpty() && !state.isLoading -> NoResults()
            else -> SearchResults(
                results = state.results,
                onClick = { navController.navigate("${Route.StockDetailScreen.path}/${it.symbol}") }
            )
        }
    }
}