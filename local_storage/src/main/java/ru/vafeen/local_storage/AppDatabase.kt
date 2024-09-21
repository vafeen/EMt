package ru.vafeen.local_storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.vafeen.local_storage.dao.VacancyEntityDao
import ru.vafeen.local_storage.dao.VacancyIDEntityDao
import ru.vafeen.local_storage.entity.VacancyEntity
import ru.vafeen.local_storage.entity.VacancyIDEntity
import ru.vafeen.local_storage.type_converters.StrListConverters

@Database(
    entities = [VacancyIDEntity::class,
        VacancyEntity::class], version = 1
)
@TypeConverters(StrListConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vacancyIDEntityDao(): VacancyIDEntityDao
    abstract fun vacancyEntityDao(): VacancyEntityDao
}
