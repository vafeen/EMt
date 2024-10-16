package com.example.emtask.presentation.noui.di

import com.example.emtask.presentation.noui.AppCoroutineDispatchers
import com.example.emtask.presentation.ui.components.adapters.OffersAdapter
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val koinServicesModule = module {
    singleOf(::AppCoroutineDispatchers)
    singleOf(::OffersAdapter)
}