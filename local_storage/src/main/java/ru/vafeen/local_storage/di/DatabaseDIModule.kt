package ru.vafeen.local_storage.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.vafeen.local_storage.AppDatabase
import ru.vafeen.local_storage.DatabaseInfo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseDIModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext applicationContext: Context): AppDatabase =
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, DatabaseInfo.NAME)
            .build()

}