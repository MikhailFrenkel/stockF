package com.frenkel.stockf.features.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.frenkel.stockf.R
import com.frenkel.ui_kit.ui.components.BottomBar
import com.frenkel.stockf.features.main.home.HomeTab
import com.frenkel.stockf.features.main.markets.MarketsTab
import com.frenkel.ui_kit.ui.models.TabItem
import com.frenkel.stockf.features.main.profile.ProfileTab
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.coroutineScope
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
            title = stringResource(R.string.profile_tab),
            selectedIcon = R.drawable.ic_profile_solid,
            unselectedIcon = R.drawable.ic_profile_linear
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

                2 -> ProfileTab(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}