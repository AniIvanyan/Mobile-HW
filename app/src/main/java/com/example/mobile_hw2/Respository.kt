package com.example.mobile_hw2
import retrofit2.Response

class Repository {
    private val apiKey = "0311b66fd7514d018b4195917231411"
    private val apiService = RetrofitBuilder.createApiService()

    suspend fun getWeather(city: String): Response<WeatherResponse> {
        return apiService.getWeather(apiKey, city)
    }
}
