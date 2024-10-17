package com.example.emtask.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emtask.data.database.DatabaseRepository
import com.example.emtask.data.database.entity.VacancyEntity
import com.example.emtask.presentation.noui.AppCoroutineDispatchers
import com.example.emtask.presentation.ui.VacationClickListener
import com.example.emtask.presentation.ui.components.adapters.FavouritesAdapter
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


internal class FavouritesFragmentViewModel(
    private var databaseRepository: DatabaseRepository,
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
) : ViewModel(), VacationClickListener {

    val favouritesAdapter: FavouritesAdapter = FavouritesAdapter(vacationClickListener = this)


    fun collectDataFromDB(onShowData: (Int) -> Unit) {
        viewModelScope.launch(appCoroutineDispatchers.io) {
            databaseRepository.getAllVacancy().collect { listVacancyEntity ->
                favouritesAdapter.favourites = listVacancyEntity.filter { vacancyEntity ->
                    vacancyEntity.isFavorite
                }
                withContext(appCoroutineDispatchers.main) {
                    favouritesAdapter.notifyDataSetChanged()
                    onShowData(favouritesAdapter.favourites.size)
                }
            }
        }
    }

    override fun onClickUpdateVacancy(vacancyEntity: VacancyEntity) {
        viewModelScope.launch(appCoroutineDispatchers.io) {
            databaseRepository.insertAllVacancy(vacancyEntity)
        }
    }
}