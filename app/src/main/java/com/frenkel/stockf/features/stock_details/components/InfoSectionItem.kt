package com.frenkel.stockf.features.stock_details.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.frenkel.ui_kit.ui.theme.BodyMediumMedium
import com.frenkel.ui_kit.ui.theme.BodyMediumRegular
import com.frenkel.ui_kit.ui.theme.Greyscale200
import com.frenkel.ui_kit.ui.theme.Greyscale700
import com.frenkel.ui_kit.ui.theme.descriptionTextColor
import com.frenkel.ui_kit.ui.theme.titleTextColor

@Composable
fun InfoSectionItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    isLastItem: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Text(
                text = title,
                color = descriptionTextColor(),
                style = BodyMediumRegular,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = value,
                color = titleTextColor(),
                style = BodyMediumMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (!isLastItem) {
            HorizontalDivider(
                modifier = modifier.fillMaxWidth(),
                color = if (isSystemInDarkTheme()) Greyscale700 else Greyscale200
            )
        }
    }
}