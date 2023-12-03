package com.example.mobile_hw2

import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(navController: NavController, activity: MainActivity, viewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.welcome_message),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (activity.checkLocationPermission()) {
                val weatherData = viewModel.weatherData.value

                val location = weatherData?.location
                if (location != null) {
                    CurrentLocation(location)
                }

                val current = weatherData?.current
                if (current != null) {
                    TemperatureInfo(current, viewModel.temperatureUnit.value)
                } else {
                    Text(
                        text = stringResource(R.string.weather_no),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            } else {
                Text(
                    text = stringResource(R.string.location_no),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            Button(
                onClick = {
                    if (activity.checkLocationPermission()) {
                        fetchWeatherForCurrentLocation(activity, viewModel, Repository())
                        navController.navigate("list_screen")
                    } else {
                        activity.requestLocationPermission()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(text = stringResource(R.string.list_button))
            }

            Spacer(modifier = Modifier.height(16.dp))

            IconButton(
                onClick = {
                    navController.navigate("settings_screen")
                }
            ) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
            }
        }
    }
}

private fun fetchWeatherForCurrentLocation(
    activity: MainActivity,
    viewModel: MainViewModel,
    repository: Repository
) {
    val locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    val locationListener = LocationListener { location ->
        val latitude = location.latitude
        val longitude = location.longitude

        viewModel.setLocation(latitude, longitude)
        activity.lifecycleScope.launch {
            delay(5000)
            viewModel.fetchWeatherForCurrentLocation(latitude, longitude, repository)
        }
    }

    try {
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0, 0f,
            locationListener
        )
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
}