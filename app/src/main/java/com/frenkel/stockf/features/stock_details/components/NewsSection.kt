package com.frenkel.stockf.features.stock_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.frenkel.stockf.R
import com.frenkel.ui_kit.ui.theme.BodyLargeBold
import com.frenkel.ui_kit.ui.theme.titleTextColor

@Composable
fun NewsSectionTitle(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.latest_news),
            color = titleTextColor(),
            style = BodyLargeBold
        )

        Spacer(Modifier.height(8.dp))
    }
}