package com.frenkel.ui_kit.ui.models

import androidx.annotation.DrawableRes

data class TabItem(
    val title: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
)
