package com.example.emtask.domain.repository

import com.example.emtask.data.network.NetworkRepository
import com.example.emtask.data.network.response.VacancyData
import com.example.emtask.domain.usecase.GetVacancyDataUseCase
import retrofit2.Response

internal class NetworkRepositoryImpl(
    private val getVacancyDataUseCase: GetVacancyDataUseCase
) : NetworkRepository {
    override suspend fun getJsonData(): Response<VacancyData> = getVacancyDataUseCase()
}
