package com.example.emtask.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emtask.data.database.type_converters.OfferToOfferEntityConverter
import com.example.emtask.data.database.type_converters.VacancyToVacancyEntityConverter
import com.example.emtask.domain.repository.DatabaseRepositoryImpl
import com.example.emtask.domain.repository.NetworkRepositoryImpl
import com.example.emtask.presentation.noui.AppCoroutineDispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val databaseRepositoryImpl: DatabaseRepositoryImpl,
    private val networkRepositoryImpl: NetworkRepositoryImpl,
    val appCoroutineDispatchers: AppCoroutineDispatchers,
) : ViewModel() {
    private val _countOfFavourites = MutableSharedFlow<Int>(0)
    val countOfFavourites = _countOfFavourites.asSharedFlow()

    init {
        viewModelScope.launch(appCoroutineDispatchers.main) {
            databaseRepositoryImpl.getAllVacancy().collect { list ->
                _countOfFavourites.emit(list.filter { it.isFavorite }.size)
            }
        }
    }

    suspend fun loadData() {
        networkRepositoryImpl.getJsonData().body()?.let {
            if (databaseRepositoryImpl.getAllVacancy().first()
                    .isEmpty()
            ) { // чтобы не происходила перезапись данных
                databaseRepositoryImpl.insertAllVacancy(*it.vacancies.map { vacancy ->
                    VacancyToVacancyEntityConverter.toVacancyEntity(vacancy)
                }.toTypedArray())
                databaseRepositoryImpl.insertAllOfferEntity(it.offers.map { offer ->
                    OfferToOfferEntityConverter.toOfferEntity(offer = offer)
                })
            }
        }
    }
}