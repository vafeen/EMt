package ru.vafeen.network

import okhttp3.ResponseBody
import retrofit2.Response
import ru.vafeen.network.response.VacancyData
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val gDriveService: GDriveService) {
    suspend fun getJsonData(): Response<VacancyData> = gDriveService.getJsonData()
}