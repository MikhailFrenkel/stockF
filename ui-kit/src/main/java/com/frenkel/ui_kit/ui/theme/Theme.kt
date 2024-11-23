package com.frenkel.ui_kit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Primary600,
    secondary = Secondary600,
    tertiary = Greyscale800,
    background = Greyscale900
)

private val LightColorScheme = lightColorScheme(
    primary = Primary600,
    secondary = Secondary600,
    tertiary = Greyscale50,
    background = White
)

@Composable
fun FTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}