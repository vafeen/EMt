package com.example.emtask.data.di

import androidx.room.Room
import com.example.emtask.data.database.AppDatabase
import com.example.emtask.data.database.DatabaseInfo
import com.example.emtask.data.network.GDriveService
import com.example.emtask.data.network.GDriveServiceLink
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val koinDataDIModule = module {
    single<GDriveService> {
        Retrofit.Builder()
            .baseUrl(GDriveServiceLink.BASE_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GDriveService::class.java)
    }
    single<AppDatabase> {
        Room
            .databaseBuilder(androidContext(), AppDatabase::class.java, DatabaseInfo.NAME)
            .build()
    }
}
