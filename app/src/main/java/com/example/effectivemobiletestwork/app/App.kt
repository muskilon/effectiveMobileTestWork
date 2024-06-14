package com.example.effectivemobiletestwork.app

import android.app.Application
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate
import dataModules
import domainModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import viewModelModules

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appResources = resources
        startKoin {
            androidContext(this@App)
            modules(dataModules, domainModules, viewModelModules)
        }

        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        )
    }
    companion object {
        private var appResources: Resources? = null
        fun getAppResources(): Resources? {
            return appResources
        }
    }
}
