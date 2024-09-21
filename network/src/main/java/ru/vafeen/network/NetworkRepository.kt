package ru.vafeen.network

import retrofit2.Response
import ru.vafeen.network.response.VacancyData

class NetworkRepository(private val gDriveService: GDriveService) {
    suspend fun getJsonData(): Response<VacancyData> = gDriveService.getJsonData()
}