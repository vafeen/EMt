package ru.vafeen.local_storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.vafeen.local_storage.entity.VacancyIDEntity

@Dao
interface VacancyIDEntityDao {

    @Query("SELECT * FROM vacancyidentity")
    fun getAll(): Flow<List<VacancyIDEntity>>

    @Insert
    suspend fun insertAll(vararg ids: VacancyIDEntity)

    @Delete
    suspend fun delete(vararg id: VacancyIDEntity)
}