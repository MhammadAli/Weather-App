package com.example.weatherapp.ui.today

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentTodayBinding
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.example.weatherapp.viewmodel.WeatherViewModelFactory
import java.math.BigDecimal

class TodayFragment : Fragment() {

    private var _binding: FragmentTodayBinding? = null
    lateinit var viewModel: WeatherViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodayBinding.inflate(inflater, container, false)

        val repository = WeatherRepository(long = 29.924526, lat = 31.205753)
        val viewModelFactory = WeatherViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[WeatherViewModel::class.java]
        viewModel.getWeather()
        viewModel.weatherResponse.observe(viewLifecycleOwner) { weatherResponse ->
            if (weatherResponse.isSuccessful) {
                Log.d(
                    "TodayFragment",
                    "onCreateView:${weatherResponse.body()?.data?.get(0)?.cityName} "
                )
                binding.cityName.text = weatherResponse.body()?.data?.get(0)?.cityName
                binding.todayDate.text = weatherResponse.body()?.data?.get(0)?.datetime
                binding.weatherDescription.text =
                    weatherResponse.body()?.data?.get(0)?.weather?.description
                binding.tempDegree.text =
                    weatherResponse.body()?.data?.get(0)?.temp?.toInt().toString() + " c"
                binding.windDegree.text =
                    BigDecimal(weatherResponse.body()?.data?.get(0)?.windSpd!!).setScale(
                        2,
                        BigDecimal.ROUND_HALF_UP
                    ).toPlainString() + " m/s"
                binding.humidityDegree.text =
                    weatherResponse.body()?.data?.get(0)?.rh.toString() + "%"
                binding.sunriseTime.text = weatherResponse.body()?.data?.get(0)?.sunrise
                binding.sunsetTime.text = weatherResponse.body()?.data?.get(0)?.sunset
                binding.cloudDegree.text =
                    weatherResponse.body()?.data?.get(0)?.clouds?.toString() + "%"
                viewModel.getWeatherDescriptionImage(
                    binding.weatherDescriptionImage,
                    weatherResponse
                )
            } else {
                Log.e("TodayFragment", "onCreateView: ${weatherResponse.errorBody()}")
            }
        }




        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}