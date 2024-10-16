package com.example.emtask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.emtask.data.database.dao.OfferEntityDao
import com.example.emtask.data.database.dao.VacancyEntityDao
import com.example.emtask.data.database.entity.OfferEntity
import com.example.emtask.data.database.entity.VacancyEntity
import com.example.emtask.data.database.type_converters.StrListConverters


@Database(
    entities = [VacancyEntity::class,
        OfferEntity::class], version = 1
)
@TypeConverters(StrListConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vacancyEntityDao(): VacancyEntityDao
    abstract fun offerEntityDao(): OfferEntityDao
}
