package com.example.emtask.domain.usecase

import com.example.emtask.data.database.AppDatabase
import com.example.emtask.data.database.DatabaseRepository
import com.example.emtask.data.database.entity.OfferEntity
import kotlinx.coroutines.flow.Flow

class GetAllOfferEntityUseCase(
    private val db: AppDatabase
) {
    operator fun invoke(): Flow<List<OfferEntity>> = db.offerEntityDao().getAll()
}