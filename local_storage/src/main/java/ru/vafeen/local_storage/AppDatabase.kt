package ru.vafeen.local_storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.vafeen.local_storage.dao.OfferEntityDao
import ru.vafeen.local_storage.dao.VacancyEntityDao
import ru.vafeen.local_storage.entity.OfferEntity
import ru.vafeen.local_storage.entity.VacancyEntity
import ru.vafeen.local_storage.type_converters.StrListConverters

@Database(
    entities = [VacancyEntity::class,
        OfferEntity::class], version = 1
)
@TypeConverters(StrListConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vacancyEntityDao(): VacancyEntityDao
    abstract fun offerEntityDao(): OfferEntityDao
}
