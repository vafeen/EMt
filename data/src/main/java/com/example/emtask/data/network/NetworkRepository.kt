package com.example.emtask.data.network

import retrofit2.Response
import com.example.emtask.data.network.response.VacancyData

interface NetworkRepository {
    suspend fun getJsonData(): Response<VacancyData>
}