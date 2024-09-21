package ru.vafeen.emtask.noui.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.vafeen.emtask.ui.components.adapters.OffersAdapter
import ru.vafeen.emtask.ui.components.viewmodels.FavouritesFragmentViewModel
import ru.vafeen.emtask.ui.components.viewmodels.SearchFragmentViewModel

val koinMainDIModule = module {
    viewModelOf(::FavouritesFragmentViewModel)
    viewModelOf(::SearchFragmentViewModel)
    single { OffersAdapter(androidContext()) }
}