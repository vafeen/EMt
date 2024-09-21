package ru.vafeen.emtask.ui.components.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject
import ru.vafeen.emtask.ui.components.VacationClickListener
import ru.vafeen.emtask.ui.components.adapters.OffersAdapter
import ru.vafeen.emtask.ui.components.adapters.VacanciesAdapter
import ru.vafeen.local_storage.DatabaseRepository
import ru.vafeen.local_storage.entity.VacancyEntity

class SearchFragmentViewModel(
    private val databaseRepository: DatabaseRepository,
) : ViewModel(), VacationClickListener {

    private var allVacanciesAreDisplayed = false
    var mainButtonText = ""

    val vacanciesAdapter: VacanciesAdapter = VacanciesAdapter(vacationClickListener = this)

    init {
        collectDataFromDB()
    }

    val offersAdapter: OffersAdapter by inject(clazz = OffersAdapter::class.java)
    fun collectDataFromLocalDB(modifyButtonText: (vacanciesSize: Int, offersSize: Int) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.getAllVacancy().collect {
                if (allVacanciesAreDisplayed) {
                    vacanciesAdapter.vacancies = it
                } else {
                    vacanciesAdapter.vacancies = if (it.size > 2) it.subList(0, 2) else it
                }
                withContext(Dispatchers.Main) {
                    vacanciesAdapter.notifyDataSetChanged()
                    modifyButtonText(it.size, offersAdapter.offers.size)
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.getAllOfferEntity().collect {
                offersAdapter.offers = it
                withContext(Dispatchers.Main) {
                    offersAdapter.notifyDataSetChanged()
                }
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