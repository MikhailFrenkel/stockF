package com.frenkel.data

import com.frenkel.data.models.Currency
import com.frenkel.data.models.RequestResult
import com.frenkel.data.models.StockSymbolDto
import com.frenkel.database.models.CurrencyDbo
import com.frenkel.database.models.StockDbo
import com.frenkel.finnhub_client.models.StockSymbol

internal fun String.toCurrency(): Currency {
    return when (this.uppercase()) {
        "USD" -> Currency.USD
        else -> Currency.Undefined(this)
    }
}

internal fun <T : Any> Result<T>.toRequestResult(): RequestResult<T> {
    return when {
        isSuccess -> RequestResult.Success(getOrThrow())
        isFailure -> RequestResult.Error(error = this.exceptionOrNull())
        else -> error("Not Implemented")
    }
}

internal fun <I : Any, O : Any> RequestResult<I>.map(mapper: (I) -> O): RequestResult<O> {
    return when (this) {
        is RequestResult.Success -> RequestResult.Success(mapper(data))
        is RequestResult.Error -> RequestResult.Error(data?.let(mapper), error = this.error)
        is RequestResult.InProgress -> RequestResult.InProgress(data?.let(mapper))
    }
}

internal fun StockSymbol.toStockSymbolDto(): StockSymbolDto {
    return StockSymbolDto(
        symbol = this.symbol,
        description = this.description,
        currency = this.currency.toCurrency(),
    )
}

internal fun StockDbo.toStockSymbolDto(): StockSymbolDto = StockSymbolDto(
    symbol = symbol,
    description = description,
    currency = currency.code.toCurrency(),
    price = price,
    percentChange = percentChange
)

internal fun StockSymbolDto.toStockDbo(): StockDbo = StockDbo(
    symbol = symbol,
    description = description,
    currency = CurrencyDbo(
        code = currency.code,
        symbol = currency.symbol
    ),
    price = price,
    percentChange = percentChange
)