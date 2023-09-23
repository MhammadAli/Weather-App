package com.example.weatherapp.repository

import com.example.weatherapp.model.Weather
import com.example.weatherapp.retrofit.RetrofitInstance
import retrofit2.Response

class WeatherRepository(
    private val long: Double,
    private val lat: Double,

    ) {

    suspend fun getWeather(): Response<Weather> = RetrofitInstance.api.getWeather(
        long = long,
        lat = lat
    )
}