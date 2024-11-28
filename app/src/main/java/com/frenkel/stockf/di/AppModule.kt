package com.frenkel.stockf.di

import com.frenkel.data.FinnhubRepository
import com.frenkel.data.FinnhubRepositoryImpl
import com.frenkel.database.StocksDatabase
import com.frenkel.database_android.StocksRoomDatabase
import com.frenkel.finnhub_client.BuildFinnhubApi
import com.frenkel.finnhub_client.FinnhubApi
import com.frenkel.finnhub_client.FinnhubWebSocket
import com.frenkel.finnhub_client.FinnhubWebSocketImpl
import com.frenkel.stockf.BuildConfig
import com.frenkel.stockf.features.main.home.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single {
        BuildFinnhubApi(
            baseUrl = BuildConfig.FINNHUB_API_BASE_URL,
            apiKey = BuildConfig.FINNHUB_API_KEY,
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
            apiKey = BuildConfig.FINNHUB_API_KEY
        )
    }.bind<FinnhubWebSocket>()

    single { StocksRoomDatabase(get()) }.bind<StocksDatabase>()
    single { FinnhubRepositoryImpl(get(), get(), get()) }.bind<FinnhubRepository>()

    viewModelOf(::HomeViewModel)
}