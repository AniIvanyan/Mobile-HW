package com.example.mobile_hw2

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

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
