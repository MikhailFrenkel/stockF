package com.frenkel.stockf.features.stock_details.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.frenkel.stockf.R
import com.frenkel.stockf.features.stock_details.models.StockInfoUI
import com.frenkel.ui_kit.ui.models.TabItem
import com.frenkel.ui_kit.ui.theme.*
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

@Composable
internal fun InfoSection(
    stockInfo: StockInfoUI,
    modifier: Modifier = Modifier,
) {
    val tabs = persistentListOf(
        stringResource(R.string.overview),
        stringResource(R.string.company)
    )

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val selectedTabIndex by remember { derivedStateOf { pagerState.currentPage } }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.background,
            indicator = { tabPositions ->
                if (selectedTabIndex < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = Secondary600
                    )
                }
            }
        ) {
            val selectedTabColor = if (isSystemInDarkTheme()) Secondary600 else Primary600

            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    selectedContentColor = selectedTabColor,
                    unselectedContentColor = Greyscale500,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = title,
                            style = if (selectedTabIndex == index) BodyLargeBold else BodyLargeRegular
                        )
                    },
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            pageSpacing = 24.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            when (selectedTabIndex) {
                0 -> OverviewTab(stockInfo)
                1 -> CompanyTab(stockInfo)
            }
        }
    }
}