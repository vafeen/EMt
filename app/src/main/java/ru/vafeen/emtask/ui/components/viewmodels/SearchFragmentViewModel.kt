package ru.vafeen.emtask.ui.components.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.vafeen.emtask.ui.components.adapters.OffersAdapter
import ru.vafeen.emtask.ui.components.adapters.VacanciesAdapter
import ru.vafeen.network.NetworkRepository
import ru.vafeen.network.response.VacancyData
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {
    private var vacancyData: VacancyData? = null
    var allVacanciesAreDisplayed = false
    var mainButtonText = ""

    @Inject
    lateinit var vacanciesAdapter: VacanciesAdapter

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

    private fun displayAllOffers() {
        vacancyData?.let {
            offersAdapter.offers = it.offers
            offersAdapter.notifyDataSetChanged()
        }
    }

    fun displayAllVacancies() {
        vacancyData?.let {
            vacanciesAdapter.vacancies = it.vacancies
            vacanciesAdapter.notifyDataSetChanged()
            allVacanciesAreDisplayed = true
        }
    }

    fun displayPreviewVacancies() {
        vacancyData?.let {
            vacanciesAdapter.vacancies = it.vacancies.subList(0, 2)
            vacanciesAdapter.notifyDataSetChanged()
            allVacanciesAreDisplayed = false
        }
    }
}