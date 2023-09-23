package com.example.weatherapp.viewmodel

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.R
import com.example.weatherapp.model.Weather
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    val weatherResponse: MutableLiveData<Response<Weather>> = MutableLiveData()
    fun getWeather() = viewModelScope.launch {

        weatherResponse.value = repository.getWeather()
    }

    fun getWeatherDescriptionImage(image: ImageView, weatherResponse: Response<Weather>) = when {
        weatherResponse.body()?.data?.get(0)?.weather?.code!! < 300 -> {
            image.setImageResource(R.drawable.storm)
        }

        weatherResponse.body()?.data?.get(0)?.weather?.code!! < 500 -> {
            image.setImageResource(R.drawable.drizzle)
        }

        weatherResponse.body()?.data?.get(0)?.weather?.code!! < 600 -> {
            image.setImageResource(R.drawable.rainy)
        }

        weatherResponse.body()?.data?.get(0)?.weather?.code!! < 700 -> {
            image.setImageResource(R.drawable.snowy)
        }

        weatherResponse.body()?.data?.get(0)?.weather?.code!! < 800 -> {
            image.setImageResource(R.drawable.fog)
        }

        weatherResponse.body()?.data?.get(0)?.weather?.code!! == 800 -> {
            image.setImageResource(R.drawable.clear_sky)
        }

        else -> {
            image.setImageResource(R.drawable.cloudy)
        }
    }

}