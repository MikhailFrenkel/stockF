package com.frenkel.stockf

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.frenkel.stockf.features.main.MainScreen

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
    }
}