package com.frenkel.stockf.features.main.markets.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.frenkel.stockf.features.common.components.Card
import com.frenkel.stockf.features.main.markets.models.SymbolLookupUI
import com.frenkel.ui_kit.ui.theme.MediumFontSize
import com.frenkel.ui_kit.ui.theme.Satoshi
import com.frenkel.ui_kit.ui.theme.SmallFontSize
import com.frenkel.ui_kit.ui.theme.descriptionTextColor
import com.frenkel.ui_kit.ui.theme.titleTextColor

@Composable
fun SearchResult(
    item: SymbolLookupUI,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 24.dp)
        ) {
            Text(
                text = item.displaySymbol,
                color = titleTextColor(),
                fontFamily = Satoshi,
                fontSize = MediumFontSize,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.description,
                color = descriptionTextColor(),
                fontFamily = Satoshi,
                fontSize = SmallFontSize,
                fontWeight = FontWeight.Normal
            )
        }
    }
}