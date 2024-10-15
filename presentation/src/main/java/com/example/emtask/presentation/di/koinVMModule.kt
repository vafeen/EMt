package com.example.emtask.presentation.di

import com.example.emtask.presentation.viewmodels.FavouritesFragmentViewModel
import com.example.emtask.presentation.viewmodels.MainActivityViewModel
import com.example.emtask.presentation.viewmodels.SearchFragmentViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val koinVMModule = module {
    viewModelOf(::FavouritesFragmentViewModel)
    viewModelOf(::SearchFragmentViewModel)
    viewModelOf(::MainActivityViewModel)
}