package ru.vafeen.emtask.ui.components.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.vafeen.emtask.ui.components.VacationClickListener
import ru.vafeen.emtask.ui.components.adapters.OffersAdapter
import ru.vafeen.emtask.ui.components.adapters.VacanciesAdapter
import ru.vafeen.local_storage.DatabaseRepository
import ru.vafeen.local_storage.VacancyID
import ru.vafeen.network.NetworkRepository
import ru.vafeen.network.response.VacancyData
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: DatabaseRepository,
) : ViewModel(), VacationClickListener {

    private var vacancyData: VacancyData? = null
    var allVacanciesAreDisplayed = false
    var mainButtonText = ""


    var vacanciesAdapter: VacanciesAdapter = VacanciesAdapter(vacationClickListener = this)

    init {
        collectDataFromDB()
    }

    @Inject
    lateinit var offersAdapter: OffersAdapter
    fun collectDataFromGDrive(modifyButtonText: (VacancyData) -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            vacancyData = networkRepository.getJsonData().body()
            vacancyData?.let {
                displayPreviewVacancies()
                displayAllOffers()
                modifyButtonText(it)
            }
        }
    }

    private fun collectDataFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.getAllVacancyID().collect {
                vacanciesAdapter.ids = it
                Log.d("id", "$it")
            }
        }
    }

    private fun displayAllOffers() {
        vacancyData?.let {
            offersAdapter.offers = it.offers
            offersAdapter.notifyDataSetChanged()
        }
    }

    fun displayAllVacancies() {
        vacancyData?.let {
            vacanciesAdapter.vacancies = it.vacancies.toMutableList()
            vacanciesAdapter.notifyDataSetChanged()
            allVacanciesAreDisplayed = true
        }
    }

    fun displayPreviewVacancies() {
        vacancyData?.let {
            vacanciesAdapter.vacancies = it.vacancies.subList(0, 2).toMutableList()
            vacanciesAdapter.notifyDataSetChanged()
            allVacanciesAreDisplayed = false
        }
    }

    override fun onClickAddVacancyToFavouriteByIDListener(vacancyID: VacancyID) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.insertVacancyID(vacancyID)
        }
    }

    override fun onClickRemoveVacancyFromFavouriteByIDListener(vacancyID: VacancyID) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.deleteVacancyID(vacancyID)
        }
    }
}