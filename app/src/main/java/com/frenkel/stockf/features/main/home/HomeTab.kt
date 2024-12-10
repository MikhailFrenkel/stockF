package com.frenkel.stockf.features.main.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.frenkel.stockf.R
import com.frenkel.stockf.Route
import com.frenkel.stockf.features.common.ErrorMessage
import com.frenkel.stockf.features.main.home.components.Header
import com.frenkel.stockf.features.common.ProgressIndicator
import com.frenkel.stockf.features.main.home.components.StocksList
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeTab(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    HomeTab(
        navController = navController,
        state = state,
        modifier = modifier
            .padding(horizontal = 24.dp)
    )
}

@Composable
private fun HomeTab(
    navController: NavController,
    state: HomeState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(modifier) {
        Header()

        when {
            state.isLoading -> ProgressIndicator()
            state.error != null -> ErrorMessage(state.error)
            state.stocks.isNotEmpty() -> StocksList(state) {
                navController.navigate("${Route.StockDetailScreen.path}/${it.symbol}")
            }
            else -> Toast.makeText(
                context,
                stringResource(R.string.sth_went_wrong),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

