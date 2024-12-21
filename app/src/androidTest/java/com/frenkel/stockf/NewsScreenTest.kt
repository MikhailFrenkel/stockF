package com.frenkel.stockf

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.frenkel.stockf.features.main.news.NewsTab
import com.frenkel.ui_kit.ui.theme.FTheme
import org.junit.Rule
import org.junit.Test
import org.koin.compose.KoinContext

@OptIn(ExperimentalTestApi::class)
class NewsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun newsScreen_Test() {
        composeTestRule.setContent {
            FTheme {
                KoinContext {
                    NewsTab()
                }
            }
        }

        composeTestRule.onNodeWithText("No News").assertIsDisplayed()
        composeTestRule.waitUntilAtLeastOneExists(tagContains("news_item_"),5_000)
        composeTestRule.onAllNodes(tagContains("news_item_"))[0].performClick()
    }
}