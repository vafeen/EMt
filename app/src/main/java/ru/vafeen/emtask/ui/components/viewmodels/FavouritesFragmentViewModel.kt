package ru.vafeen.emtask.ui.components.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.vafeen.emtask.ui.components.VacationClickListener
import ru.vafeen.emtask.ui.components.adapters.FavouritesAdapter
import ru.vafeen.local_storage.DatabaseRepository
import ru.vafeen.local_storage.entity.VacancyEntity
import javax.inject.Inject

@HiltViewModel
class FavouritesFragmentViewModel @Inject constructor(
    private var databaseRepository: DatabaseRepository
) : ViewModel(), VacationClickListener {

    val favouritesAdapter: FavouritesAdapter = FavouritesAdapter(vacationClickListener = this)


    fun collectDataFromDB(onShowData: (Int) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.getAllVacancy().collect { listVacancyEntity ->
                favouritesAdapter.favourites = listVacancyEntity.filter { vacancyEntity ->
                    vacancyEntity.isFavorite
                }
                withContext(Dispatchers.Main) {
                    favouritesAdapter.notifyDataSetChanged()
                    onShowData(favouritesAdapter.favourites.size)
                }
            }
        }
    }

    override fun onClickAddVacancyToFavouriteByIDListener(
        vacancyEntity: VacancyEntity
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.insertAllVacancy(vacancyEntity)
        }
    }

    override fun onClickRemoveVacancyFromFavouriteByIDListener(
        vacancyEntity: VacancyEntity
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.insertAllVacancy(vacancyEntity)
        }
    }
}