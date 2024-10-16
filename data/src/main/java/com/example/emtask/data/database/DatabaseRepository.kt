package com.example.emtask.data.database

import com.example.emtask.data.database.entity.OfferEntity
import com.example.emtask.data.database.entity.VacancyEntity
import kotlinx.coroutines.flow.Flow


interface DatabaseRepository {
    suspend fun insertAllVacancy(vararg vacancies: VacancyEntity)
    suspend fun deleteVacancy(vararg vacancies: VacancyEntity)
    fun getAllVacancy(): Flow<List<VacancyEntity>>

    suspend fun insertAllOfferEntity(offerEntities: List<OfferEntity>)
    suspend fun deleteOfferEntity(offerEntities: List<OfferEntity>)
    fun getAllOfferEntity(): Flow<List<OfferEntity>>
}