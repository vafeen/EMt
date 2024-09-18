package ru.vafeen.network

import okhttp3.ResponseBody
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val gDriveService: GDriveService) {
    suspend fun getJsonData(): ResponseBody = gDriveService.getJsonData()
}