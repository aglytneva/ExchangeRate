package com.example.exchangerate

import android.app.Application
import com.example.exchangerate.di.currencyMainScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(currencyMainScreenModule)
        }


    }
}