package com.example.poster.di.app

import android.app.Application
import com.example.poster.di.appModule
import com.example.poster.di.dataModule
import com.example.poster.di.domainModule
import com.google.firebase.FirebaseApp


import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(appModule, dataModule, domainModule)
        }
    }
}