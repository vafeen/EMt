package com.example.emtask.domain.usecase

import com.example.emtask.data.database.DatabaseRepository
import com.example.emtask.data.database.entity.OfferEntity

class DeleteOfferEntityUseCase(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(offerEntities: List<OfferEntity>) {
        databaseRepository.deleteOfferEntity(offerEntities = offerEntities)
    }
}