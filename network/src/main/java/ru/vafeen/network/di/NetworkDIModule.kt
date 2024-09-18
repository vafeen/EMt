package ru.vafeen.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.vafeen.network.GDriveService
import ru.vafeen.network.GDriveServiceLink
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkDIModule {

    @Provides
    @Singleton
    fun provideGHDService(): GDriveService = Retrofit.Builder()
        .baseUrl(GDriveServiceLink.BASE_LINK)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(GDriveService::class.java)
}
