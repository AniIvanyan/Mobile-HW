package com.example.mobile_hw2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CityName(city: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = city,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

fun getCityImage(city: String): Int {
    return when (city) {
        "Yerevan" -> R.drawable.yerevan
        "Washington" -> R.drawable.washington
        "Madrid" -> R.drawable.madrid
        "Paris" -> R.drawable.paris
        "Tokyo" -> R.drawable.tokyo
        "Warsaw" -> R.drawable.warsaw
        else -> R.drawable.no_image
    }
}

@Composable
fun getCityDescription(city: String): String {
    val resourceId = when (city) {
        "Yerevan" -> R.string.yerevan_description
        "Washington" -> R.string.washington_description
        "Madrid" -> R.string.madrid_description
        "Paris" -> R.string.paris_description
        "Tokyo" -> R.string.tokyo_description
        "Warsaw" -> R.string.warsaw_description
        else -> R.string.default_description
    }
    return stringResource(resourceId)
}

@Composable
fun getTemperatureSymbol(unit: TemperatureUnit): String {
    return when (unit) {
        TemperatureUnit.Celsius -> "°C"
        TemperatureUnit.Fahrenheit -> "°F"
    }
}

@Composable
fun TemperatureInfo(current: Current, temperatureUnit: TemperatureUnit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        val temperature = when (temperatureUnit) {
            TemperatureUnit.Celsius -> current.temp_c
            TemperatureUnit.Fahrenheit -> current.temp_f
        }

        if (temperature != null) {
            Text(
                text = "Temperature: $temperature${getTemperatureSymbol(temperatureUnit)}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        } else if (current.humidity != null) {
            Text(
                text = "Humidity: ${current.humidity}%",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun CurrentLocation(location: Location) {
    Text(
        text = "Current Location: ${location.name}",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}