package com.example.emtask.domain.di

import com.example.emtask.domain.usecase.DeleteOfferEntityUseCase
import com.example.emtask.domain.usecase.DeleteVacancyUseCase
import com.example.emtask.domain.usecase.GetAllOfferEntityUseCase
import com.example.emtask.domain.usecase.GetAllVacancyUseCase
import com.example.emtask.domain.usecase.GetVacancyDataUseCase
import com.example.emtask.domain.usecase.InsertAllOfferEntityUseCase
import com.example.emtask.domain.usecase.InsertAllVacancyUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::DeleteOfferEntityUseCase)
    singleOf(::DeleteVacancyUseCase)
    singleOf(::GetAllOfferEntityUseCase)
    singleOf(::GetAllVacancyUseCase)
    singleOf(::GetVacancyDataUseCase)
    singleOf(::InsertAllOfferEntityUseCase)
    singleOf(::InsertAllVacancyUseCase)
}