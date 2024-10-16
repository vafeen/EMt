package com.example.emtask.domain.usecase

import com.example.emtask.data.database.DatabaseRepository
import com.example.emtask.data.database.entity.OfferEntity

class InsertAllOfferEntityUseCase(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(offerEntity: List<OfferEntity>) {
        databaseRepository.insertAllOfferEntity(offerEntities = offerEntity)
    }
}