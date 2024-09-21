package ru.vafeen.emtask.ui.components.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject
import ru.vafeen.emtask.ui.components.VacationClickListener
import ru.vafeen.emtask.ui.components.adapters.OffersAdapter
import ru.vafeen.emtask.ui.components.adapters.VacanciesAdapter
import ru.vafeen.local_storage.DatabaseRepository
import ru.vafeen.local_storage.entity.VacancyEntity


class SearchFragmentViewModel(
    private val databaseRepository: DatabaseRepository,
) : ViewModel(), VacationClickListener {

    var allVacanciesAreDisplayed = false
    var mainButtonText = ""
    val vacanciesAdapter: VacanciesAdapter = VacanciesAdapter(vacationClickListener = this)
    private var vacanciesRealSize = 0

    val offersAdapter: OffersAdapter by inject(clazz = OffersAdapter::class.java) {
        parametersOf(this)
    }

    fun collectDataFromLocalDB(modifyButtonText: (vacanciesSize: Int, offersSize: Int) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.getAllVacancy().collect {
                vacanciesAdapter.vacancies = if (allVacanciesAreDisplayed) {
                    it
                } else {
                    if (it.size > 2) it.subList(0, 2) else it
                }
                vacanciesRealSize = it.size
                withContext(Dispatchers.Main) {
                    modifyButtonText(vacanciesRealSize, offersAdapter.offers.size)
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.getAllOfferEntity().collect {
                offersAdapter.offers = it
                withContext(Dispatchers.Main) {
                    offersAdapter.notifyDataSetChanged()
                    modifyButtonText(vacanciesRealSize, offersAdapter.offers.size)
                }
            }
        }
    }

    fun displayVacancies(all: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.getAllVacancy().first().let {
                vacanciesAdapter.vacancies =
                    if (it.size > 2 && !all) it.subList(0, 2) else it
            }
            withContext(Dispatchers.Main) {
                vacanciesAdapter.notifyDataSetChanged()
            }
            allVacanciesAreDisplayed = all
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