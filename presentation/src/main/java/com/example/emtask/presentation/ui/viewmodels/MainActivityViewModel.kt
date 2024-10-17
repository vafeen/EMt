package com.example.emtask.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emtask.data.database.DatabaseRepository
import com.example.emtask.data.database.type_converters.OfferToOfferEntityConverter
import com.example.emtask.data.database.type_converters.VacancyToVacancyEntityConverter
import com.example.emtask.data.network.NetworkRepository
import com.example.emtask.presentation.noui.AppCoroutineDispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

internal class MainActivityViewModel(
    private val databaseRepository: DatabaseRepository,
    private val networkRepository: NetworkRepository,
    val appCoroutineDispatchers: AppCoroutineDispatchers,
) : ViewModel() {
    private val _countOfFavourites = MutableSharedFlow<Int>(0)
    val countOfFavourites = _countOfFavourites.asSharedFlow()

    init {
        viewModelScope.launch(appCoroutineDispatchers.main) {
            databaseRepository.getAllVacancy().collect { list ->
                _countOfFavourites.emit(list.filter { it.isFavorite }.size)
            }
        }
    }

    suspend fun loadData() {
        networkRepository.getJsonData().body()?.let {
            if (databaseRepository.getAllVacancy().first()
                    .isEmpty()
            ) { // чтобы не происходила перезапись данных
                databaseRepository.insertAllVacancy(*it.vacancies.map { vacancy ->
                    VacancyToVacancyEntityConverter.toVacancyEntity(vacancy)
                }.toTypedArray())
                databaseRepository.insertAllOfferEntity(it.offers.map { offer ->
                    OfferToOfferEntityConverter.toOfferEntity(offer = offer)
                })
            }
        }
    }
}