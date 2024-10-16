package com.example.emtask.data.network

import retrofit2.Response
import retrofit2.http.GET
import com.example.emtask.data.network.response.VacancyData

interface GDriveService {
    @GET(GDriveServiceLink.EndPoint.JSON)
    suspend fun getJsonData(): Response<VacancyData>

}