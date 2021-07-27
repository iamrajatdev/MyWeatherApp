package com.example.weatherzone.data

import com.example.weatherzone.model.Main
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("data/2.5/weather?")
    suspend fun getCityDetails(
        @Query("q") cityName: String?,
        @Query("appid") api_key: String?,
        @Query("units") unit: String?,
    ): Response<Main>
}