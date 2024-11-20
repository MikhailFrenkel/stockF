package com.frenkel.stockf.features.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.frenkel.stockf.R
import com.frenkel.stockf.features.main.components.BottomBar
import com.frenkel.stockf.features.main.home.HomeTab
import com.frenkel.stockf.features.main.markets.MarketsTab
import com.frenkel.stockf.features.main.models.TabItem
import com.frenkel.stockf.features.main.profile.ProfileTab
import kotlinx.collections.immutable.persistentListOf

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

    var selectedTab by remember { mutableStateOf(tabs.first()) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(
                selectedItem = selectedTab,
                items = tabs,
                onClick = { selectedTab = it }
            )
        }
    ) { innerPadding ->
        when (selectedTab.selectedIcon) {
            R.drawable.ic_home_solid -> HomeTab(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )

            R.drawable.ic_chart_solid -> MarketsTab(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )

            R.drawable.ic_profile_solid -> ProfileTab(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}