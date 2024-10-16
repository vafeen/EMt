package com.example.emtask.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.emtask.data.database.entity.VacancyEntity

@Dao
interface VacancyEntityDao {
    @Query("SELECT * FROM vacancyentity")
    fun getAll(): Flow<List<VacancyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg vacancyEntity: VacancyEntity)

    @Delete
    suspend fun delete(vararg vacancyEntity: VacancyEntity)
}