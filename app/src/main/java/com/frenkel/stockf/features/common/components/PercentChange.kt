package com.frenkel.stockf.features.common.components

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frenkel.stockf.features.main.models.DisplayableNumber
import com.frenkel.ui_kit.ui.theme.*

@Composable
fun PercentChange(
    percentChange: DisplayableNumber,
    iconSize: Dp = 14.dp,
    textSize: TextUnit = 12.sp,
    spacing: Dp = 6.dp,
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
                .size(iconSize)
                .background(
                    color = color,
                    shape = CircleShape
                )
        )

        Spacer(modifier = Modifier.width(spacing))

        Text(
            text = "${percentChange.formatted}%",
            color = color,
            fontFamily = Satoshi,
            fontSize = textSize,
            fontWeight = FontWeight.Medium
        )
    }
}