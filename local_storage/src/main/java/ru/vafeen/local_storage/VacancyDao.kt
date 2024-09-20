package ru.vafeen.local_storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VacancyDao {

    @Query("SELECT * FROM vacancyid")
    fun getAll(): Flow<List<VacancyID>>

    @Insert
    suspend fun insertAll(vararg ids: VacancyID)

    @Delete
    suspend fun delete(vararg id: VacancyID)
}