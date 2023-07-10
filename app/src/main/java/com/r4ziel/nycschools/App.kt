package com.r4ziel.nycschools

import android.app.Application
import com.r4ziel.nycschools.modules.appModule
import com.r4ziel.nycschools.modules.repositoryModule
import com.r4ziel.nycschools.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Jarvis Charles on 7/7/23.
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule,repositoryModule, viewModelModule))
        }
    }
}