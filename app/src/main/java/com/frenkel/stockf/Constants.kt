package com.frenkel.stockf

sealed class Route(val path: String) {

    data object MainScreen: Route("main")
}