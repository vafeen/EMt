package ru.vafeen.local_storage.type_converters

import ru.vafeen.local_storage.entity.OfferEntity
import ru.vafeen.network.response.Offer

object OfferToOfferEntityConverter {
    fun toOfferEntity(offer: Offer): OfferEntity = OfferEntity(
        id = offer.id,
        title = offer.title,
        link = offer.link,
        buttonText = offer.button?.text
    )
}