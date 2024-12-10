package com.frenkel.data

import com.frenkel.data.models.AggregatesResponseDto
import com.frenkel.data.models.AggregatesTradeDto
import com.frenkel.data.models.CompanyNewsDto
import com.frenkel.data.models.CompanyProfile2Dto
import com.frenkel.data.models.Currency
import com.frenkel.data.models.QuoteDto
import com.frenkel.data.models.RealTimeTradesDto
import com.frenkel.data.models.RequestResult
import com.frenkel.data.models.StockSymbolDto
import com.frenkel.data.models.TradeDto
import com.frenkel.database.models.CurrencyDbo
import com.frenkel.database.models.StockDbo
import com.frenkel.finnhub_client.models.CompanyNews
import com.frenkel.finnhub_client.models.CompanyProfile2
import com.frenkel.finnhub_client.models.Quote
import com.frenkel.finnhub_client.models.RealTimeTradesResponse
import com.frenkel.finnhub_client.models.StockSymbol
import com.frenkel.finnhub_client.models.Trade
import com.frenkel.polygon_client.models.AggregatesResponse
import java.util.Date

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
    percentChange = percentChange,
    favorite = favorite,
    imageUrl = imageUrl,
)

internal fun StockSymbolDto.toStockDbo(): StockDbo = StockDbo(
    symbol = symbol,
    description = description,
    currency = CurrencyDbo(
        code = currency.code,
        symbol = currency.symbol
    ),
    price = price,
    percentChange = percentChange,
    favorite = favorite,
    imageUrl = imageUrl,
)

internal fun CompanyProfile2.toDto(): CompanyProfile2Dto = CompanyProfile2Dto(
    country = country,
    currency = currency.toCurrency(),
    exchange = exchange,
    finnhubIndustry = finnhubIndustry,
    ipo = ipo,
    logo = logo,
    marketCapitalization = marketCapitalization,
    name = name,
    phone = phone,
    shareOutstanding = shareOutstanding,
    ticker = ticker,
    webUrl = webUrl
)

internal fun Quote.toDto(): QuoteDto = QuoteDto(
    currentPrice = currentPrice,
    change = change,
    percentChange = percentChange,
    highPriceOfTheDay = highPriceOfTheDay,
    lowPriceOfTheDay = lowPriceOfTheDay,
    openPriceOfTheDay = openPriceOfTheDay,
    previousClosePrice = previousClosePrice,
    date = Date(timestamp * 1000)
)

internal fun CompanyNews.toDto(): CompanyNewsDto = CompanyNewsDto(
    category = category,
    timestamp = Date(timestamp * 1000),
    headline = headline,
    id = id,
    imageUrl = imageUrl,
    related = related,
    source = source,
    summary = summary,
    url = url
)

internal fun RealTimeTradesResponse.toDto(): RealTimeTradesDto = RealTimeTradesDto(
    type = type,
    data = data.map { it.toDto() }
)

internal fun Trade.toDto(): TradeDto = TradeDto(
    symbol = symbol,
    lastPrice = lastPrice,
    timestamp = timestamp,
    volume = volume
)

internal fun com.frenkel.polygon_client.models.Trade.toDto(): AggregatesTradeDto = AggregatesTradeDto(
    closePrice = closePrice,
    highPrice = highPrice,
    lowPrice = lowPrice,
    openPrice = openPrice,
    date = Date(timestamp),
    volume = volume
)

internal fun AggregatesResponse.toDto(): AggregatesResponseDto = AggregatesResponseDto(
    ticker = ticker,
    queryCount = queryCount,
    resultsCount = resultsCount,
    adjusted = adjusted,
    results = results.map { it.toDto() },
    status = status,
    requestId = requestId,
    count = count
)