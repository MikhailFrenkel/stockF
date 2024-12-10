package com.frenkel.stockf.features.main.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.frenkel.stockf.features.common.components.ErrorMessage
import com.frenkel.stockf.features.main.news.components.NewsList
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsTab(
    viewModel: NewsViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when {
        state.error != null -> ErrorMessage(state.error!!)
        else -> NewsList(
            state = state,
            onRefresh = {
                viewModel.onAction(NewsAction.OnPullToRefresh)
            },
            modifier = modifier
        )
    }
}