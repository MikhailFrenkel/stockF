package com.frenkel.stockf.features.stock_details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.frenkel.stockf.R
import com.frenkel.stockf.features.common.Card
import com.frenkel.stockf.features.stock_details.models.CompanyNewsUI
import com.frenkel.stockf.utils.openCustomTab
import com.frenkel.stockf.utils.toRelativeDateString
import com.frenkel.ui_kit.ui.theme.BodyLargeBold
import com.frenkel.ui_kit.ui.theme.BodySmallBold
import com.frenkel.ui_kit.ui.theme.BodySmallRegular
import com.frenkel.ui_kit.ui.theme.Greyscale300
import com.frenkel.ui_kit.ui.theme.Greyscale700
import com.frenkel.ui_kit.ui.theme.Success600
import com.frenkel.ui_kit.ui.theme.descriptionTextColor
import com.frenkel.ui_kit.ui.theme.titleTextColor

@Composable
fun NewsItem(
    item: CompanyNewsUI,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { item.url.openCustomTab(context) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = null,
                placeholder = painterResource(R.drawable.placeholder),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(Modifier.width(16.dp))

            Column {
                Text(
                    text = item.headline,
                    color = titleTextColor(),
                    style = BodyLargeBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(6.dp))

                Row {
                    Text(
                        text = item.source,
                        color = Success600,
                        style = BodySmallBold
                    )

                    Text(
                        text = "·",
                        color = if (isSystemInDarkTheme()) Greyscale700 else Greyscale300,
                        style = BodySmallBold,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Text(
                        text = item.date.toRelativeDateString(context),
                        color = descriptionTextColor(),
                        style = BodySmallRegular
                    )
                }
            }
        }
    }
}