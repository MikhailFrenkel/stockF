package com.frenkel.stockf.features.main.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.frenkel.stockf.features.main.models.TabItem
import com.frenkel.ui_kit.ui.theme.*
import kotlinx.collections.immutable.ImmutableList

@Composable
fun BottomBar(
    selectedItem: TabItem,
    items: ImmutableList<TabItem>,
    onClick: (TabItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedIconColor = if (isSystemInDarkTheme()) White else Primary600
    val unselectedIconColor = if (isSystemInDarkTheme()) Greyscale500 else Greyscale400
    val navBarBgColor = if (isSystemInDarkTheme()) Greyscale900 else White

    NavigationBar(
        modifier = modifier,
        containerColor = navBarBgColor
    ) {
        items.forEach { tabItem ->
            val isSelected = tabItem == selectedItem
            val color = if (isSelected) selectedIconColor else unselectedIconColor

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            if (isSelected) tabItem.selectedIcon else tabItem.unselectedIcon
                        ),
                        tint = color,
                        contentDescription = tabItem.title
                    )
                },
                label = {
                    Text(
                        text = tabItem.title,
                        color = color,
                        fontSize = SmallFontSize,
                        fontFamily = Satoshi,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                },
                selected = isSelected,
                onClick = { onClick(tabItem) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                ),
            )
        }
    }
}