package ru.vafeen.emtask.app

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.vafeen.local_storage.DatabaseRepository
import ru.vafeen.local_storage.type_converters.VacancyToVacancyEntityConverter
import ru.vafeen.network.NetworkRepository
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var databaseRepository: DatabaseRepository

    @Inject
    lateinit var networkRepository: NetworkRepository
    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            networkRepository.getJsonData().body()?.let {
                val newVacancies = it.vacancies.map { vacancy ->
                    Log.d("vacancy", "vacancy = $vacancy")
                    VacancyToVacancyEntityConverter.toVacancyEntity(vacancy)
                }.toTypedArray()
                databaseRepository.insertAllVacancy(*newVacancies)
            }
        }
    }
}