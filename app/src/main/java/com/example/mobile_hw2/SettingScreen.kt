package com.example.mobile_hw2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController, viewModel: MainViewModel = viewModel()) {
    var selectedUnit by remember { mutableStateOf(viewModel.temperatureUnit.value) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.settings_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            RadioButton(
                selected = selectedUnit == TemperatureUnit.Celsius,
                onClick = { selectedUnit = TemperatureUnit.Celsius },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = stringResource(R.string.celsius))
        }

        Row(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            RadioButton(
                selected = selectedUnit == TemperatureUnit.Fahrenheit,
                onClick = { selectedUnit = TemperatureUnit.Fahrenheit },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = stringResource(R.string.fahrenheit))
        }

        Button(
            onClick = {
                viewModel.setTemperatureUnit(selectedUnit ?: TemperatureUnit.Celsius)
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.save_button))
        }
    }
}
