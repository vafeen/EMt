package com.example.emtask.domain.usecase

import com.example.emtask.data.database.AppDatabase
import com.example.emtask.data.database.entity.VacancyEntity

internal class InsertAllVacancyUseCase(
    private val db: AppDatabase
) {
    suspend operator fun invoke(vararg vacancyEntity: VacancyEntity) {
        db.vacancyEntityDao().insertAll(vacancyEntity = vacancyEntity)
    }
}