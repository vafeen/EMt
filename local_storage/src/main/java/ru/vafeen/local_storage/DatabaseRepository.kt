package ru.vafeen.local_storage

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val database: AppDatabase
) {
    private val vacancyDao = database.vacancyDao()
    suspend fun insertVacancyID(vararg ids: VacancyID) = vacancyDao.insertAll(*ids)
    suspend fun deleteVacancyID(vararg ids: VacancyID) = vacancyDao.delete()
    fun getAllVacancyID(): Flow<List<VacancyID>> = vacancyDao.getAll()
}