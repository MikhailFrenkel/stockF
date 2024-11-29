package com.frenkel.stockf

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.frenkel.data.models.StockSymbolDto
import com.frenkel.stockf.features.common.NotFoundScreen
import com.frenkel.stockf.features.main.MainScreen
import com.frenkel.stockf.features.main.models.StockUI
import com.frenkel.stockf.features.stock_details.StockDetailScreen
import kotlinx.serialization.json.Json

private val json: Json = Json

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.MainScreen.path,
    ) {
        composable(Route.MainScreen.path) {
            MainScreen(navController)
        }

        val stockDetailScreenArgumentName = "symbol"
        composable(
            route = "${Route.StockDetailScreen.path}/{$stockDetailScreenArgumentName}",
            arguments = listOf(
                navArgument(stockDetailScreenArgumentName) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val symbol = backStackEntry.arguments?.getString(stockDetailScreenArgumentName)
            NavigateIfArgumentNotNull(symbol, navController) {
                StockDetailScreen(navController, it)
            }
        }
    }
}

@Composable
fun <T> NavigateIfArgumentNotNull(
    argument: T?,
    navController: NavController,
    content: @Composable (T) -> Unit
) {
    if (argument != null) {
        content(argument)
    } else {
        NotFoundScreen(navController)
    }
}