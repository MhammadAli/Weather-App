package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val `data`: List<Data>
)