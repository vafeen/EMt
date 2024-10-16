package com.example.emtask.domain.di

import com.example.emtask.domain.repository.DatabaseRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::DatabaseRepositoryImpl)
}