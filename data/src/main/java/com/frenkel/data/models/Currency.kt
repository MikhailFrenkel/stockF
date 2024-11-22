package com.frenkel.data.models

sealed class Currency(
    val code: String,
    val symbol: String? = null,
) {

    val symbolOrCode: String
        get() = symbol ?: code

    data object USD : Currency(symbol = "$", code = "USD")
    class Undefined(code: String) : Currency(code = code)
}
