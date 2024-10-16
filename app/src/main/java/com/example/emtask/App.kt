package com.example.emtask

import android.app.Application
import com.example.emtask.data.network.di.koinNetworkDIModule
import com.example.emtask.domain.di.repositoryModule
import com.example.emtask.domain.di.useCaseModule
import com.example.emtask.presentation.di.koinVMModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(koinVMModule, koinNetworkDIModule, useCaseModule, repositoryModule)
        }
    }
}
