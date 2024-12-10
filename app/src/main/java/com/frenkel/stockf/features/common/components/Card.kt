package com.frenkel.stockf.features.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.frenkel.ui_kit.ui.theme.Greyscale100
import com.frenkel.ui_kit.ui.theme.Greyscale50
import com.frenkel.ui_kit.ui.theme.Greyscale700
import com.frenkel.ui_kit.ui.theme.Greyscale800

@Composable
fun Card(
    cornerRadius: Dp = 12.dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        color = if (isSystemInDarkTheme()) Greyscale800 else Greyscale50,
        shape = RoundedCornerShape(cornerRadius),
        border = BorderStroke(
            width = 1.dp,
            color = if (isSystemInDarkTheme()) Greyscale700 else Greyscale100
        ),
        content = content
    )
}