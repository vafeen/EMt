package com.example.emtask.domain.repository

import com.example.emtask.data.network.NetworkRepository
import com.example.emtask.data.network.response.VacancyData
import com.example.emtask.domain.usecase.GetVacancyDataUseCase
import retrofit2.Response

class NetworkRepositoryImpl(
    private val vacancyDataUseCase: GetVacancyDataUseCase
) : NetworkRepository {
    override suspend fun getJsonData(): Response<VacancyData> = vacancyDataUseCase()
}
