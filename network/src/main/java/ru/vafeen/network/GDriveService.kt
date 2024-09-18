package ru.vafeen.network

import okhttp3.ResponseBody
import retrofit2.http.GET

interface GDriveService {
    @GET(GDriveServiceLink.EndPoint.JSON)
    suspend fun getJsonData(): ResponseBody

}