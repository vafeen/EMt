package ru.vafeen.emtask.ui.components

import ru.vafeen.local_storage.entity.VacancyEntity
import ru.vafeen.local_storage.entity.VacancyIDEntity

interface VacationClickListener {
    fun onClickAddVacancyToFavouriteByIDListener(
        vacancyEntity: VacancyEntity,
        vacancyIDEntity: VacancyIDEntity
    )

    fun onClickRemoveVacancyFromFavouriteByIDListener(
        vacancyEntity: VacancyEntity,
        vacancyIDEntity: VacancyIDEntity
    )

}