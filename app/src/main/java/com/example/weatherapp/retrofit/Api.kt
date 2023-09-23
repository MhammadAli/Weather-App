package com.example.weatherapp.retrofit

import com.example.weatherapp.model.Weather
import com.example.weatherapp.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("current")
    suspend fun getWeather(
        @Query("lon") long: Double,
        @Query("lat") lat: Double,
        @Query("key") apiKey: String = API_KEY
    ): Response<Weather>
}