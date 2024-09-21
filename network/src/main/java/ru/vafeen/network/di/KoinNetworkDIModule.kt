package ru.vafeen.network.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.vafeen.network.GDriveService
import ru.vafeen.network.GDriveServiceLink
import ru.vafeen.network.NetworkRepository

val koinNetworkDIModule = module {
    single<GDriveService> {
        Retrofit.Builder()
            .baseUrl(GDriveServiceLink.BASE_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GDriveService::class.java)
    }
    singleOf(::NetworkRepository)
}
