package com.example.emtask.presentation.ui

import com.example.emtask.data.database.entity.VacancyEntity

internal interface VacationClickListener {

    fun onClickUpdateVacancy(
        vacancyEntity: VacancyEntity,
    )

}