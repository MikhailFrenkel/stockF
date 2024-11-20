package com.frenkel.stockf.features.main.models

import androidx.annotation.DrawableRes

data class TabItem(
    val title: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
)
