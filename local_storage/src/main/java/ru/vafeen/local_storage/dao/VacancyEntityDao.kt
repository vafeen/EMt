package ru.vafeen.local_storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.vafeen.local_storage.entity.VacancyEntity

@Dao
interface VacancyEntityDao {
    @Query("SELECT * FROM vacancyentity")
    fun getAll(): Flow<List<VacancyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg vacancyEntity: VacancyEntity)

    @Delete
    suspend fun delete(vararg vacancyEntity: VacancyEntity)
}