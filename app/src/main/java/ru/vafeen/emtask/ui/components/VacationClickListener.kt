package ru.vafeen.emtask.ui.components

import ru.vafeen.local_storage.VacancyID

interface VacationClickListener {
    fun onClickAddVacancyToFavouriteByIDListener(vacancyID: VacancyID)
    fun onClickRemoveVacancyFromFavouriteByIDListener(vacancyID: VacancyID)

}