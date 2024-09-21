package ru.vafeen.local_storage

import kotlinx.coroutines.flow.Flow
import ru.vafeen.local_storage.entity.VacancyEntity
import ru.vafeen.local_storage.entity.VacancyIDEntity
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val database: AppDatabase
) {
    private val vacancyIDEntityDao = database.vacancyIDEntityDao()
    private val vacancyEntityDao = database.vacancyEntityDao()
    suspend fun insertVacancyID(vararg ids: VacancyIDEntity) = vacancyIDEntityDao.insertAll(*ids)
    suspend fun deleteVacancyID(vararg ids: VacancyIDEntity) = vacancyIDEntityDao.delete(*ids)
    fun getAllVacancyID(): Flow<List<VacancyIDEntity>> = vacancyIDEntityDao.getAll()

    suspend fun insertAllVacancy(vararg vacancies: VacancyEntity) =
        vacancyEntityDao.insertAll(*vacancies)

    suspend fun deleteVacancy(vararg vacancies: VacancyEntity) =
        vacancyEntityDao.delete(*vacancies)

    fun getAllVacancy(): Flow<List<VacancyEntity>> = vacancyEntityDao.getAll()


}