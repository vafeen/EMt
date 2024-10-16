package com.example.emtask.presentation.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emtask.data.database.DatabaseRepository
import com.example.emtask.data.database.entity.VacancyEntity
import com.example.emtask.domain.repository.DatabaseRepositoryImpl
import com.example.emtask.presentation.noui.AppCoroutineDispatchers
import com.example.emtask.presentation.ui.VacationClickListener
import com.example.emtask.presentation.ui.components.adapters.OffersAdapter
import com.example.emtask.presentation.ui.components.adapters.VacanciesAdapter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject


class SearchFragmentViewModel(
    private val databaseRepositoryImpl: DatabaseRepository,
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
) : ViewModel(), VacationClickListener {

    var allVacanciesAreDisplayed = false
    var mainButtonText = ""
    val vacanciesAdapter: VacanciesAdapter = VacanciesAdapter(vacationClickListener = this)
    private var vacanciesRealSize = 0

    val offersAdapter: OffersAdapter by inject(clazz = OffersAdapter::class.java) {
        parametersOf(this)
    }

    fun collectDataFromLocalDB(modifyButtonText: (vacanciesSize: Int, offersSize: Int) -> Unit) {
        viewModelScope.launch(appCoroutineDispatchers.io) {
            databaseRepositoryImpl.getAllVacancy().collect {
                withContext(appCoroutineDispatchers.main) {
                    vacanciesAdapter.vacancies = if (allVacanciesAreDisplayed) {
                        it
                    } else {
                        if (it.size > 2) it.subList(0, 2) else it
                    }
                    Log.d("vacancies", it.joinToString {
                        it.toString() + "\n"
                    })
                    vacanciesRealSize = it.size
                    modifyButtonText(vacanciesRealSize, offersAdapter.offers.size)
                }
            }
        }

        viewModelScope.launch(appCoroutineDispatchers.io) {
            databaseRepositoryImpl.getAllOfferEntity().collect {
                withContext(appCoroutineDispatchers.main) {
                    offersAdapter.offers = it
                    modifyButtonText(vacanciesRealSize, offersAdapter.offers.size)
                }
            }
        }
    }

    fun displayVacancies(all: Boolean) {
        viewModelScope.launch(appCoroutineDispatchers.io) {
            val vacancies = databaseRepositoryImpl.getAllVacancy().first()
            withContext(appCoroutineDispatchers.main) {
                vacanciesAdapter.vacancies =
                    if (vacancies.size > 2 && !all) vacancies.subList(0, 2) else vacancies
                vacanciesAdapter.notifyDataSetChanged()
            }
            allVacanciesAreDisplayed = all
        }
    }

    override fun onClickUpdateVacancy(vacancyEntity: VacancyEntity) {
        viewModelScope.launch(appCoroutineDispatchers.io) {
            databaseRepositoryImpl.insertAllVacancy(vacancyEntity)
        }
    }
}