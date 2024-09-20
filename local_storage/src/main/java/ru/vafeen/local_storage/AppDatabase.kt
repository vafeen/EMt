package ru.vafeen.local_storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VacancyID::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vacancyDao(): VacancyDao

}
