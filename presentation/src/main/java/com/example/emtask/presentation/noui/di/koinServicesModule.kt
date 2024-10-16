package com.example.emtask.presentation.noui.di

import com.example.emtask.presentation.noui.AppCoroutineDispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val koinServicesModule = module {
    singleOf(::AppCoroutineDispatchers)
}