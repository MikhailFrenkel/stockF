package com.frenkel.stockf.features.common

import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import com.frenkel.stockf.utils.openCustomTab

@Composable
fun HyperlinkText(
    text: String,
    url: String,
    color: Color,
    style: TextStyle,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Text(
        text = text,
        color = color,
        style = style,
        textDecoration = TextDecoration.Underline,
        modifier = modifier
            .clickable { url.openCustomTab(context) }
    )
}