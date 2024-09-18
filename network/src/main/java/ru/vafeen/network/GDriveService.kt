package ru.vafeen.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import ru.vafeen.network.response.VacancyData

interface GDriveService {
    @GET(GDriveServiceLink.EndPoint.JSON)
    suspend fun getJsonData(): Response<VacancyData>

}