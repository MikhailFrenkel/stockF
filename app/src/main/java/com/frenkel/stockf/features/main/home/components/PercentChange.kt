package com.frenkel.stockf.features.main.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.frenkel.stockf.features.main.models.DisplayableNumber
import com.frenkel.ui_kit.ui.theme.*

@Composable
fun PercentChange(
    percentChange: DisplayableNumber,
    modifier: Modifier = Modifier
) {
    val color = if (percentChange.value < 0) Error600 else Success600

    Row(
        modifier = modifier
    ) {
        Icon(
            imageVector = if (percentChange.value < 0) {
                Icons.Default.KeyboardArrowDown
            } else {
                Icons.Default.KeyboardArrowUp
            },
            contentDescription = percentChange.formatted,
            tint = White,
            modifier = Modifier
                .size(14.dp)
                .background(
                    color = color,
                    shape = CircleShape
                )
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = "${percentChange.formatted}%",
            color = color,
            fontFamily = Satoshi,
            fontSize = SmallFontSize,
            fontWeight = FontWeight.Medium
        )
    }
}