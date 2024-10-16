package com.example.emtask.data.database.type_converters

import com.example.emtask.data.database.entity.OfferEntity
import com.example.emtask.data.network.response.Offer

object OfferToOfferEntityConverter {
    fun toOfferEntity(offer: Offer): OfferEntity = OfferEntity(
        id = offer.id,
        title = offer.title,
        link = offer.link,
        buttonText = offer.button?.text
    )
}