package com.example.mobile_hw2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> get() = _weatherData

    private val _location = MutableLiveData<Pair<Double, Double>>()
    val location: LiveData<Pair<Double, Double>> get() = _location

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

    fun setLocation(latitude: Double, longitude: Double) {
        _location.value = Pair(latitude, longitude)
    }
}
