package ru.vafeen.emtask.app

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.vafeen.emtask.noui.di.koinMainDIModule
import ru.vafeen.local_storage.DatabaseRepository
import ru.vafeen.local_storage.di.koinDatabaseModule
import ru.vafeen.local_storage.type_converters.OfferToOfferEntityConverter
import ru.vafeen.local_storage.type_converters.VacancyToVacancyEntityConverter
import ru.vafeen.network.NetworkRepository
import ru.vafeen.network.di.koinNetworkDIModule


class App : Application() {

    private val databaseRepository: DatabaseRepository by inject()
    private val networkRepository: NetworkRepository by inject()


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(koinDatabaseModule, koinNetworkDIModule, koinMainDIModule)
        }

        CoroutineScope(Dispatchers.IO).launch {
            networkRepository.getJsonData().body()?.let {
                val newVacancies = it.vacancies.map { vacancy ->
                    VacancyToVacancyEntityConverter.toVacancyEntity(vacancy)
                }.toTypedArray()
                val newOffers = it.offers.map { offer ->
                    OfferToOfferEntityConverter.toOfferEntity(offer = offer)
                }
                if (databaseRepository.getAllVacancy().first()
                        .isEmpty()
                ) { // чтобы не происходила перезапись данных
                    databaseRepository.insertAllVacancy(*newVacancies)
                    databaseRepository.insertAllOfferEntity(newOffers)
                }
            }
        }
    }
}