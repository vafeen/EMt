package com.example.emtask.domain.usecase

import com.example.emtask.data.database.DatabaseRepository
import com.example.emtask.data.database.entity.VacancyEntity
import kotlinx.coroutines.flow.Flow

class GetAllVacancyUseCase(
    private val databaseRepository: DatabaseRepository
) {
    operator fun invoke(): Flow<List<VacancyEntity>> = databaseRepository.getAllVacancy()
}