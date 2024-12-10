package com.frenkel.stockf.features.stock_details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.frenkel.stockf.features.common.components.Card
import com.frenkel.stockf.features.stock_details.models.ChartTimeRange
import com.frenkel.ui_kit.ui.theme.BodySmallBold
import com.frenkel.ui_kit.ui.theme.BodySmallMedium
import com.frenkel.ui_kit.ui.theme.Greyscale400
import com.frenkel.ui_kit.ui.theme.Greyscale700
import com.frenkel.ui_kit.ui.theme.Primary600
import com.frenkel.ui_kit.ui.theme.White

@Composable
fun ChartHeader(
    items: List<ChartTimeRange>,
    selectedItem: ChartTimeRange,
    onClick: (ChartTimeRange) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        cornerRadius = 6.dp,
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.padding(4.dp)
        ) {
            items.forEach {
                Item(
                    item = stringResource(it.stringId),
                    isSelected = it == selectedItem,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onClick(it) }
                )
            }
        }
    }
}

@Composable
private fun Item(
    item: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    val textColor = if (!isSelected) {
        Greyscale400
    } else {
        if (isSystemInDarkTheme()) White else Primary600
    }

    val backgroundColor = if (!isSelected) {
        Color.Transparent
    } else {
        if (isSystemInDarkTheme()) Greyscale700 else White
    }

    Surface(
        modifier = modifier
            .height(24.dp),
        color = backgroundColor,
        shape = if (isSelected) RoundedCornerShape(4.dp) else RectangleShape,
        shadowElevation = if (isSelected) 4.dp else 0.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item,
                color = textColor,
                style = if (isSelected) BodySmallBold else BodySmallMedium
            )
        }
    }
}