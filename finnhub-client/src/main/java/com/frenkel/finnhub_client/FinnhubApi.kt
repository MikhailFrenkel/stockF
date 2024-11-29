package com.frenkel.finnhub_client

import com.frenkel.finnhub_client.converters.DateConverterFactory
import com.frenkel.finnhub_client.converters.ExchangeTickerConverterFactory
import com.frenkel.finnhub_client.interceptors.FinnhubApiKeyInterceptor
import com.frenkel.finnhub_client.models.CompanyNews
import com.frenkel.finnhub_client.models.CompanyProfile2
import com.frenkel.finnhub_client.models.ExchangeTicker
import com.frenkel.finnhub_client.models.MarketStatus
import com.frenkel.finnhub_client.models.Quote
import com.frenkel.finnhub_client.models.StockSymbol
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

interface FinnhubApi {

    @GET("stock/symbol")
    suspend fun fetchStockSymbols(
        @Query("exchange") exchange: ExchangeTicker = ExchangeTicker.US
    ) : Result<List<StockSymbol>>

    @GET("quote")
    suspend fun fetchQuote(
        @Query("symbol") symbol: String
    ) : Result<Quote>

    @GET("stock/market-status")
    suspend fun fetchMarketStatus(
        @Query("exchange") exchange: ExchangeTicker = ExchangeTicker.US
    ) : Result<MarketStatus>

    @GET("stock/profile2")
    suspend fun fetchCompanyProfile2(
        @Query("symbol") symbol: String
    ) : Result<CompanyProfile2>

    @GET("company-news")
    suspend fun fetchCompanyNews(
        @Query("symbol") symbol: String,
        @Query("from") from: Date,
        @Query("to") to: Date
    ) : Result<List<CompanyNews>>
}

fun BuildFinnhubApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json
): FinnhubApi {
    val jsonConverterFactory = json.asConverterFactory("application/json; charset=UTF8".toMediaType())

    val modifiedOkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(FinnhubApiKeyInterceptor(apiKey))
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .addConverterFactory(ExchangeTickerConverterFactory())
        .addConverterFactory(DateConverterFactory())
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(modifiedOkHttpClient)
        .build()

    return retrofit.create()
}