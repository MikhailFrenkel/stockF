package com.frenkel.stockf.features.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.frenkel.stockf.R
import com.frenkel.ui_kit.ui.theme.BodyLargeMedium
import com.frenkel.ui_kit.ui.theme.HeadingH3
import com.frenkel.ui_kit.ui.theme.descriptionTextColor
import com.frenkel.ui_kit.ui.theme.titleTextColor

@Composable
fun NotFoundScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = { TopBar { navController.navigateUp() } }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.oops),
                color = titleTextColor(),
                style = HeadingH3
            )

            Text(
                text = stringResource(R.string.something_went_wrong),
                color = descriptionTextColor(),
                style = BodyLargeMedium
            )
        }
    }
}