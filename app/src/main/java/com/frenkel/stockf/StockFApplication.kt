package com.frenkel.stockf

import android.app.Application
import com.frenkel.stockf.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class StockFApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StockFApplication)
            androidLogger()
            modules(appModule)
        }
    }
}