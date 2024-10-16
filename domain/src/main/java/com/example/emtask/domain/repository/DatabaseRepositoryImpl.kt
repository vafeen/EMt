package com.example.emtask.domain.repository

import com.example.emtask.data.database.DatabaseRepository
import com.example.emtask.data.database.entity.OfferEntity
import com.example.emtask.data.database.entity.VacancyEntity
import com.example.emtask.domain.usecase.DeleteOfferEntityUseCase
import com.example.emtask.domain.usecase.DeleteVacancyUseCase
import com.example.emtask.domain.usecase.GetAllOfferEntityUseCase
import com.example.emtask.domain.usecase.GetAllVacancyUseCase
import com.example.emtask.domain.usecase.InsertAllOfferEntityUseCase
import com.example.emtask.domain.usecase.InsertAllVacancyUseCase
import kotlinx.coroutines.flow.Flow

class DatabaseRepositoryImpl(
    private val insertAllVacancyUseCase: InsertAllVacancyUseCase,
    private val deleteVacancyUseCase: DeleteVacancyUseCase,
    private val getAllVacancyUseCase: GetAllVacancyUseCase,
    private val insertAllOfferEntityUseCase: InsertAllOfferEntityUseCase,
    private val deleteOfferEntityUseCase: DeleteOfferEntityUseCase,
    private val getAllOfferEntityUseCase: GetAllOfferEntityUseCase,
) : DatabaseRepository {
    override suspend fun insertAllVacancy(vararg vacancyEntity: VacancyEntity) =
        insertAllVacancyUseCase(vacancyEntity = vacancyEntity)

    override suspend fun deleteVacancy(vararg vacancyEntity: VacancyEntity) =
        deleteVacancyUseCase(vacancyEntity = vacancyEntity)

    override fun getAllVacancy(): Flow<List<VacancyEntity>> = getAllVacancyUseCase()

    override suspend fun insertAllOfferEntity(offerEntities: List<OfferEntity>) =
        insertAllOfferEntityUseCase(offerEntities = offerEntities)

    override suspend fun deleteOfferEntity(offerEntities: List<OfferEntity>) =
        deleteOfferEntityUseCase(offerEntities = offerEntities)

    override fun getAllOfferEntity(): Flow<List<OfferEntity>> = getAllOfferEntityUseCase()

}