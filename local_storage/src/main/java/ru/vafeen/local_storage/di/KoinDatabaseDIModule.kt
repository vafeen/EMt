package ru.vafeen.local_storage.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.vafeen.local_storage.AppDatabase
import ru.vafeen.local_storage.DatabaseInfo
import ru.vafeen.local_storage.DatabaseRepository

val koinDatabaseModule = module {
    single<AppDatabase> {
        Room
            .databaseBuilder(androidContext(), AppDatabase::class.java, DatabaseInfo.NAME)
            .build()
    }
    singleOf(::DatabaseRepository)
}
