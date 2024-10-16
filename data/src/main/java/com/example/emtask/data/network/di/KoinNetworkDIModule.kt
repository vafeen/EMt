package com.example.emtask.data.network.di

import com.example.emtask.data.network.GDriveService
import com.example.emtask.data.network.GDriveServiceLink
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val koinNetworkDIModule = module {
    single<GDriveService> {
        Retrofit.Builder()
            .baseUrl(GDriveServiceLink.BASE_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GDriveService::class.java)
    }
}
