package com.example.emtask.presentation.noui.di

import com.example.emtask.presentation.ui.viewmodels.FavouritesFragmentViewModel
import com.example.emtask.presentation.ui.viewmodels.MainActivityViewModel
import com.example.emtask.presentation.ui.viewmodels.SearchFragmentViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val koinVMModule = module {
    viewModelOf(::FavouritesFragmentViewModel)
    viewModelOf(::SearchFragmentViewModel)
    viewModelOf(::MainActivityViewModel)
}