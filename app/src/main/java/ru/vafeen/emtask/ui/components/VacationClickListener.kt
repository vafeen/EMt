package ru.vafeen.emtask.ui.components

import ru.vafeen.local_storage.entity.VacancyEntity

interface VacationClickListener {
    fun onClickAddVacancyToFavouriteByIDListener(
        vacancyEntity: VacancyEntity,
    )

    fun onClickRemoveVacancyFromFavouriteByIDListener(
        vacancyEntity: VacancyEntity,
    )

}