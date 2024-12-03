package com.frenkel.stockf.di

import com.frenkel.data.StocksRepository
import com.frenkel.data.StocksRepositoryImpl
import com.frenkel.database.StocksDatabase
import com.frenkel.database_android.StocksRoomDatabase
import com.frenkel.finnhub_client.BuildFinnhubApi
import com.frenkel.finnhub_client.FinnhubApi
import com.frenkel.finnhub_client.FinnhubWebSocket
import com.frenkel.finnhub_client.FinnhubWebSocketImpl
import com.frenkel.polygon_client.BuildPolygonApi
import com.frenkel.polygon_client.PolygonApi
import com.frenkel.stockf.BuildConfig
import com.frenkel.stockf.features.main.home.HomeViewModel
import com.frenkel.stockf.features.stock_details.StockDetailsViewModel
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single {
        BuildFinnhubApi(
            baseUrl = BuildConfig.FINNHUB_API_BASE_URL,
            apiKey = BuildConfig.FINNHUB_API_KEY,
            json = Json {
                ignoreUnknownKeys = true
            },
            okHttpClient = OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BASIC)
                )
                .build()
        )
    }.bind<FinnhubApi>()

    single {
        FinnhubWebSocketImpl(
            baseUrl = BuildConfig.FINNHUB_WEBSOCKET_BASE_URL,
            apiKey = BuildConfig.FINNHUB_API_KEY,
            json = Json {
                ignoreUnknownKeys = true
            }
        )
    }.bind<FinnhubWebSocket>()

    single {
        BuildPolygonApi(
            baseUrl = BuildConfig.POLYGON_API_BASE_URL,
            apiKey = BuildConfig.POLYGON_API_KEY,
            json = Json {
                ignoreUnknownKeys = true
            },
            okHttpClient = OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build()
        )
    }.bind<PolygonApi>()

    single { StocksRoomDatabase(get()) }.bind<StocksDatabase>()
    single { StocksRepositoryImpl(get(), get(), get(), get()) }.bind<StocksRepository>()

    viewModelOf(::HomeViewModel)
    viewModel { (symbol: String) -> StockDetailsViewModel(symbol, get()) }
}