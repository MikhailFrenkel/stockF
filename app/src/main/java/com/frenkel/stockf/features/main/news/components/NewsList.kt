package com.frenkel.stockf.features.main.news.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.frenkel.stockf.features.common.components.Header
import com.frenkel.stockf.features.common.components.NewsItem
import com.frenkel.stockf.features.main.news.NewsState
import com.frenkel.ui_kit.ui.theme.Primary600
import com.frenkel.ui_kit.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsList(
    state: NewsState,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pullToRefreshState = rememberPullToRefreshState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Header()

        PullToRefreshBox(
            isRefreshing = state.isLoading,
            onRefresh = onRefresh,
            state = pullToRefreshState,
            indicator = {
                Indicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = state.isLoading,
                    state = pullToRefreshState,
                    containerColor = Primary600,
                    color = White
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (state.news.isEmpty()) {
                    item {
                        EmptyList()
                    }
                } else {
                    items(state.news) {
                        NewsItem(
                            item = it,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
                        )
                    }
                }
            }
        }
    }
}