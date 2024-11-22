package com.frenkel.stockf

import com.frenkel.data.models.Symbol

sealed class Route(val path: String) {

    data object MainScreen: Route("main")
}

val predefinedStockSymbols = Symbol.entries.map { it.name }.toList()