package com.frenkel.stockf

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class StockDetailsTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun stockDetails_Test() {
        val nvdaItemTag = "stock_item_nvda"
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag(nvdaItemTag),20_000)
        composeTestRule.onNodeWithTag(nvdaItemTag).performClick()
        composeTestRule.onNodeWithTag("favorite_icon_${R.drawable.ic_favorite_unselected}")
            .assertIsDisplayed()
    }
}