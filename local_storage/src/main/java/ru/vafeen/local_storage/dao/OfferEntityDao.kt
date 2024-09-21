package ru.vafeen.local_storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.vafeen.local_storage.entity.OfferEntity

@Dao
interface OfferEntityDao {
    @Query("SELECT * FROM offerentity")
    fun getAll(): Flow<List<OfferEntity>>

    @Insert
    suspend fun insertAll(offerEntities: List<OfferEntity>)

    @Delete
    suspend fun delete(offerEntities: List<OfferEntity>)
}