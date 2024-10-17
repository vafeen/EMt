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
    singleOf(::InsertAllVacancyUseCase)
    singleOf(::DeleteVacancyUseCase)
    singleOf(::GetAllVacancyUseCase)
    singleOf(::InsertAllOfferEntityUseCase)
    singleOf(::DeleteOfferEntityUseCase)
    singleOf(::GetAllOfferEntityUseCase)
    singleOf(::GetVacancyDataUseCase)
    singleOf(::DatabaseRepositoryImpl)
    singleOf(::NetworkRepositoryImpl)
    single<DatabaseRepository> {
        val databaseRepositoryImpl: DatabaseRepositoryImpl by inject<DatabaseRepositoryImpl>()
        databaseRepositoryImpl
    }
    single<NetworkRepository> {
        val networkRepositoryImpl: NetworkRepositoryImpl by inject<NetworkRepositoryImpl>()
        networkRepositoryImpl
    }
}