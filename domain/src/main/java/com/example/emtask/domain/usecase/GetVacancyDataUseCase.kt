package com.example.emtask.domain.usecase

import com.example.emtask.data.network.NetworkRepository
import com.example.emtask.data.network.response.VacancyData
import retrofit2.Response

class GetVacancyDataUseCase(
    private val networkRepository: NetworkRepository
) {
    suspend operator fun invoke(): Response<VacancyData> = networkRepository.getJsonData()
}
