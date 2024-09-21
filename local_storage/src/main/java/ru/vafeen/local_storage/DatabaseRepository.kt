package ru.vafeen.local_storage

import kotlinx.coroutines.flow.Flow
import ru.vafeen.local_storage.entity.OfferEntity
import ru.vafeen.local_storage.entity.VacancyEntity
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val database: AppDatabase
) {
    private val vacancyEntityDao = database.vacancyEntityDao()
    private val offerEntityDao = database.offerEntityDao()

    suspend fun insertAllVacancy(vararg vacancies: VacancyEntity) =
        vacancyEntityDao.insertAll(*vacancies)

    suspend fun deleteVacancy(vararg vacancies: VacancyEntity) =
        vacancyEntityDao.delete(*vacancies)

    fun getAllVacancy(): Flow<List<VacancyEntity>> = vacancyEntityDao.getAll()

    suspend fun insertAllOfferEntity(offerEntities: List<OfferEntity>) =
        offerEntityDao.insertAll(offerEntities)

    suspend fun deleteOfferEntity(offerEntities: List<OfferEntity>) =
        offerEntityDao.delete(offerEntities)

    fun getAllOfferEntity(): Flow<List<OfferEntity>> = offerEntityDao.getAll()
}