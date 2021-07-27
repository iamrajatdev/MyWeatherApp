package com.example.weatherzone.data

class CityRepository {

    suspend fun getCity(param: String?, apiKey: String) = RetrofitClient.cityAPi.getCityDetails(param,apiKey,"metric")
}