package com.adhibuchori.narumi.utils

import android.app.Application
import com.adhibuchori.narumi.di.AppModule
import com.adhibuchori.narumi.di.DatabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.NONE)
            androidContext(this@MyApplication)
            modules(DatabaseModule.getModules())
            modules(AppModule.getModules())
        }
    }
}