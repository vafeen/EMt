package com.example.emtask.domain.usecase

import com.example.emtask.data.database.AppDatabase
import com.example.emtask.data.database.entity.OfferEntity

internal class DeleteOfferEntityUseCase(
    private val db: AppDatabase
) {
    suspend operator fun invoke(offerEntities: List<OfferEntity>) {
        db.offerEntityDao().delete(offerEntities = offerEntities)
    }
}