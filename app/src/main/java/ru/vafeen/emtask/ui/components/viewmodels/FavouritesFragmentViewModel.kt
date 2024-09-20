package ru.vafeen.emtask.ui.components.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vafeen.emtask.ui.components.adapters.FavouritesAdapter
import javax.inject.Inject

@HiltViewModel
class FavouritesFragmentViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var favouritesAdapter: FavouritesAdapter
//    здесь будет функция которая будет доставать из бд данные
}