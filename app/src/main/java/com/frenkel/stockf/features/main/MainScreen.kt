package com.frenkel.stockf.features.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.frenkel.stockf.R
import com.frenkel.stockf.features.main.home.HomeTab
import com.frenkel.stockf.features.main.markets.MarketsTab
import com.frenkel.stockf.features.main.news.NewsTab
import com.frenkel.ui_kit.ui.components.BottomBar
import com.frenkel.ui_kit.ui.models.TabItem
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val tabs = persistentListOf(
        TabItem(
            title = stringResource(R.string.home_tab),
            selectedIcon = R.drawable.ic_home_solid,
            unselectedIcon = R.drawable.ic_home_linear
        ),

        TabItem(
            title = stringResource(R.string.markets_tab),
            selectedIcon = R.drawable.ic_chart_solid,
            unselectedIcon = R.drawable.ic_chart_linear
        ),

        TabItem(
            title = stringResource(R.string.news_tab),
            selectedIcon = R.drawable.ic_news_solid,
            unselectedIcon = R.drawable.ic_news_linear
        ),
    )

    val pagerState = rememberPagerState { tabs.size }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomBar(
                selectedItem = tabs[pagerState.currentPage],
                items = tabs,
                onClick = {
                    scope.launch { pagerState.scrollToPage(tabs.indexOf(it)) }
                }
            )
        }
    ) { innerPadding ->

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false
        ) { page ->
            when (page) {
                0 -> HomeTab(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )

                1 -> MarketsTab(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )

                2 -> NewsTab(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}