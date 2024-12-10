package com.frenkel.stockf.features.main.markets.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.frenkel.stockf.R
import com.frenkel.ui_kit.ui.theme.*
import com.frenkel.ui_kit.ui.theme.titleTextColor

@Composable
fun NoResults(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.no_results_found),
            color = titleTextColor(),
            style = HeadingH5
        )
    }
}