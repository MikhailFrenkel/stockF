package com.frenkel.stockf.features.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.frenkel.ui_kit.ui.theme.*

@Composable
fun Header(
    modifier: Modifier = Modifier
) {
    val titleTextColor = if (isSystemInDarkTheme()) White else Greyscale900

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(vertical = 14.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Hi, @User \uD83D\uDC4B",
            color = titleTextColor,
            fontFamily = Satoshi,
            fontWeight = FontWeight.Bold,
            fontSize = LargeFontSize
        )

        Text(
            text = "Welcome back to Stock!",
            color = Greyscale400,
            fontFamily = Satoshi,
            fontWeight = FontWeight.Medium,
            fontSize = SmallFontSize
        )
    }
}