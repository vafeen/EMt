package ru.vafeen.emtask.ui.components.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.vafeen.emtask.ui.components.adapters.FavouritesAdapter
import ru.vafeen.local_storage.DatabaseRepository
import javax.inject.Inject

@HiltViewModel
class FavouritesFragmentViewModel @Inject constructor(
    private var databaseRepository: DatabaseRepository
) : ViewModel() {

    @Inject
    lateinit var favouritesAdapter: FavouritesAdapter

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val ids = databaseRepository.getAllVacancyID().first().map {
                it.vacancyID
            }
            databaseRepository.getAllVacancy().collect { listVacancyEntity ->
                favouritesAdapter.favourites = listVacancyEntity.filter { vacancyEntity ->
                    vacancyEntity.id in ids
                }
            }
        }
    }
//    здесь будет функция которая будет доставать из бд данные
}