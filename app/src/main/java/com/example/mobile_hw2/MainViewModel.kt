package com.example.mobile_hw2

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

enum class TemperatureUnit {
    Celsius,
    Fahrenheit
}

class MainViewModel : ViewModel() {
    private val _weatherData = MutableLiveData<WeatherResponse>()
    val temperatureUnit: MutableState<TemperatureUnit> = mutableStateOf(TemperatureUnit.Celsius)
    val weatherData: LiveData<WeatherResponse> get() = _weatherData

    private val _location = MutableLiveData<Pair<Double, Double>>()

    fun fetchWeather(city: String, repository: Repository) {
        viewModelScope.launch {
            try {
                val response = repository.getWeather(city)
                if (response.isSuccessful) {
                    _weatherData.value = response.body()
                } else {
                    Log.e("Retrofit", "Failed to retrieve weather data for $city")
                }
            } catch (e: Exception) {
                Log.e("Retrofit", "Error fetching weather data", e)
            }
        }
    }

    fun fetchWeatherForCurrentLocation(
        latitude: Double,
        longitude: Double,
        repository: Repository
    ) {
        viewModelScope.launch {
            try {
                val response = repository.getWeatherForCurrentLocation(latitude, longitude)
                if (response.isSuccessful) {
                    _weatherData.value = response.body()
                } else {
                    Log.e("Retrofit", "Failed to retrieve weather data for current location")
                }
            } catch (e: Exception) {
                Log.e("Retrofit", "Error fetching weather data for current location", e)
            }
        }
    }

    fun fetchTemperature(city: String, repository: Repository) {
        viewModelScope.launch {
            try {
                val response = repository.getTemperatureForCity(city)
                if (response.isSuccessful) {
                    _weatherData.value = response.body()
                } else {
                    Log.e("Retrofit", "Failed to retrieve temperature data for $city")
                }
            } catch (e: Exception) {
                Log.e("Retrofit", "Error fetching temperature data", e)
            }
        }
    }

    fun setLocation(latitude: Double, longitude: Double) {
        _location.value = Pair(latitude, longitude)
    }

    fun setTemperatureUnit(unit: TemperatureUnit) {
        temperatureUnit.value = unit
    }
}
