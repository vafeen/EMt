package com.example.emtask.domain.usecase

import com.example.emtask.data.network.GDriveService
import com.example.emtask.data.network.response.VacancyData
import retrofit2.Response

internal class GetVacancyDataUseCase(
    private val gDriveService: GDriveService
) {
    suspend operator fun invoke(): Response<VacancyData> = gDriveService.getJsonData()
}
