package ru.vafeen.emtask.ui.components.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.vafeen.emtask.ui.components.VacationClickListener
import ru.vafeen.emtask.ui.components.adapters.OffersAdapter
import ru.vafeen.emtask.ui.components.adapters.VacanciesAdapter
import ru.vafeen.local_storage.DatabaseRepository
import ru.vafeen.local_storage.entity.VacancyEntity
import ru.vafeen.local_storage.entity.VacancyIDEntity
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository,
) : ViewModel(), VacationClickListener {

    private var allVacanciesAreDisplayed = false
    var mainButtonText = ""


    var vacanciesAdapter: VacanciesAdapter = VacanciesAdapter(vacationClickListener = this)

    init {
        collectDataFromDB()
    }

    @Inject
    lateinit var offersAdapter: OffersAdapter
    fun collectDataFromGDrive(modifyButtonText: (vacanciesSize: Int, offersSize: Int) -> Unit) {
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
//            offersAdapter.offers = it.offers
//            offersAdapter.notifyDataSetChanged()
        }
    }

    fun displayAllVacancies() {
        viewModelScope.launch(Dispatchers.Main) {
            vacanciesAdapter.vacancies = databaseRepository.getAllVacancy().first()
            vacanciesAdapter.notifyDataSetChanged()
            allVacanciesAreDisplayed = true
        }
    }


    fun displayPreviewVacancies() {
        viewModelScope.launch(Dispatchers.IO) {
            vacanciesAdapter.vacancies = databaseRepository.getAllVacancy().first().let {
                if (it.size > 2) it.subList(0, 2) else it
            }
            vacanciesAdapter.notifyDataSetChanged()
            allVacanciesAreDisplayed = false
        }
    }

    private fun collectDataFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.getAllVacancyID().collect {
                vacanciesAdapter.ids = it
            }
        }
    }


    override fun onClickAddVacancyToFavouriteByIDListener(
        vacancyEntity: VacancyEntity,
        vacancyIDEntity: VacancyIDEntity
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.insertAllVacancy(vacancyEntity)
            databaseRepository.insertVacancyID(vacancyIDEntity)
        }
    }

    override fun onClickRemoveVacancyFromFavouriteByIDListener(
        vacancyEntity: VacancyEntity,
        vacancyIDEntity: VacancyIDEntity
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.deleteVacancy(vacancyEntity)
            databaseRepository.deleteVacancyID(vacancyIDEntity)
        }
    }
}