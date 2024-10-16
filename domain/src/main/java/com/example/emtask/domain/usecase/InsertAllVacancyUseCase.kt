package com.example.emtask.domain.usecase

import com.example.emtask.data.database.DatabaseRepository
import com.example.emtask.data.database.entity.VacancyEntity

class InsertAllVacancyUseCase(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(vararg vacancies: VacancyEntity) {
        databaseRepository.insertAllVacancy(vacancies = vacancies)
    }
}