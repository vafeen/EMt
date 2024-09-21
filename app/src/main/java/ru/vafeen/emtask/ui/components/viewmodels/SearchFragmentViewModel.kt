package ru.vafeen.emtask.ui.components.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.vafeen.emtask.ui.components.VacationClickListener
import ru.vafeen.emtask.ui.components.adapters.OffersAdapter
import ru.vafeen.emtask.ui.components.adapters.VacanciesAdapter
import ru.vafeen.local_storage.DatabaseRepository
import ru.vafeen.local_storage.entity.VacancyEntity
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository,
) : ViewModel(), VacationClickListener {

    private var allVacanciesAreDisplayed = false
    var mainButtonText = ""

    val vacanciesAdapter: VacanciesAdapter = VacanciesAdapter(vacationClickListener = this)

    init {
        collectDataFromDB()
    }

    @Inject
    lateinit var offersAdapter: OffersAdapter
    fun collectDataFromLocalDB(modifyButtonText: (vacanciesSize: Int, offersSize: Int) -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            databaseRepository.getAllVacancy().collect {
                if (allVacanciesAreDisplayed) {
                    vacanciesAdapter.vacancies = it
                } else {
                    vacanciesAdapter.vacancies = if (it.size > 2) it.subList(0, 2) else it
                }
                vacanciesAdapter.notifyDataSetChanged()
                modifyButtonText(it.size, offersAdapter.offers.size)
            }
        }
        viewModelScope.launch(Dispatchers.Main) {
            databaseRepository.getAllOfferEntity().collect {
                offersAdapter.offers = it
                offersAdapter.notifyDataSetChanged()
            }
        }
    }

    fun displayAllVacancies() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.getAllVacancy().collect {
                vacanciesAdapter.vacancies = it
                withContext(Dispatchers.Main) {
                    vacanciesAdapter.notifyDataSetChanged()
                }
                allVacanciesAreDisplayed = true
            }
        }
    }


    fun displayPreviewVacancies() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.getAllVacancy().collect {
                vacanciesAdapter.vacancies =
                    if (it.size > 2 && !allVacanciesAreDisplayed) it.subList(0, 2) else it
                withContext(Dispatchers.Main) {
                    vacanciesAdapter.notifyDataSetChanged()
                }
                Log.d("collect", "displaypreview")
                allVacanciesAreDisplayed = false
            }
        }
    }

    private fun collectDataFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
//            databaseRepository.getAllVacancyID().collect {
//                vacanciesAdapter.ids = it
//            }
        }
    }


    override fun onClickAddVacancyToFavouriteByIDListener(
        vacancyEntity: VacancyEntity,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.insertAllVacancy(vacancyEntity)
        }
    }

    override fun onClickRemoveVacancyFromFavouriteByIDListener(
        vacancyEntity: VacancyEntity,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.insertAllVacancy(vacancyEntity)
        }
    }
}