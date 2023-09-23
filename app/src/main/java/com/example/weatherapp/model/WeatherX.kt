package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class WeatherX(
    @SerializedName("code")
    val code: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)