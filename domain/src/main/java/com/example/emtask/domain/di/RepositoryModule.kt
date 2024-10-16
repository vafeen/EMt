package com.example.emtask.domain.di

import com.example.emtask.data.database.DatabaseRepository
import com.example.emtask.data.network.NetworkRepository
import com.example.emtask.domain.repository.DatabaseRepositoryImpl
import com.example.emtask.domain.repository.NetworkRepositoryImpl
import com.example.emtask.domain.usecase.DeleteOfferEntityUseCase
import com.example.emtask.domain.usecase.DeleteVacancyUseCase
import com.example.emtask.domain.usecase.GetAllOfferEntityUseCase
import com.example.emtask.domain.usecase.GetAllVacancyUseCase
import com.example.emtask.domain.usecase.GetVacancyDataUseCase
import com.example.emtask.domain.usecase.InsertAllOfferEntityUseCase
import com.example.emtask.domain.usecase.InsertAllVacancyUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    // Регистрация зависимостей для DatabaseRepositoryImpl
    single { InsertAllVacancyUseCase(get()) }
    single { DeleteVacancyUseCase(get()) }
    single { GetAllVacancyUseCase(get()) }
    single { InsertAllOfferEntityUseCase(get()) }
    single { DeleteOfferEntityUseCase(get()) }
    single { GetAllOfferEntityUseCase(get()) }
    single { GetVacancyDataUseCase(get()) }
//    singleOf(::DeleteOfferEntityUseCase)
//    singleOf(::DeleteVacancyUseCase)
//    singleOf(::GetAllOfferEntityUseCase)
//    singleOf(::GetAllVacancyUseCase)
//    singleOf(::GetVacancyDataUseCase)
//    singleOf(::InsertAllOfferEntityUseCase)
//    singleOf(::InsertAllVacancyUseCase)

    single<DatabaseRepository> {
        DatabaseRepositoryImpl(
            insertAllVacancyUseCase = get(),
            deleteVacancyUseCase = get(),
            getAllVacancyUseCase = get(),
            insertAllOfferEntityUseCase = get(),
            deleteOfferEntityUseCase = get(),
            getAllOfferEntityUseCase = get()
        )
    }
    single<NetworkRepository> {
        NetworkRepositoryImpl(getVacancyDataUseCase = get())
    }
}