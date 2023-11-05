package com.example.mobile_hw2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobile_hw2.ui.theme.MobileHW2Theme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

val cityList = listOf("Yerevan", "Warsaw", "Washington", "Madrid", "Paris", "Tokyo")

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            this.navController = navController

            MobileHW2Theme {
                NavHost(
                    navController = navController,
                    startDestination = "welcome_screen"
                ) {
                    composable("welcome_screen") {
                        WelcomeScreen(navController)
                    }
                    composable("second_screen") {
                        SecondScreen(navController)
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!navController.navigateUp()) {
            super.onBackPressed()
        }
    }
}
@Composable
fun WelcomeScreen(navController: NavController) {
    Button(
        onClick = { navController.navigate("second_screen") },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Go to Second Screen")
    }
}
@Composable
fun SecondScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        // Loop through the cityList
        items(cityList) { city ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        navController.navigate("second_screen/$city")
                    }
            ) {
                // Display city name using CityItem
                CityItem(city = city) {
                    navController.navigate("second_screen/$city")
                }
            }

            // Display city image
            Image(
                painter = painterResource(id = getCityImage(city)),
                contentDescription = "City Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            // Display description of the city
            Text(
                text = getCityDescription(city),
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}


private fun getCityImage(city: String): Int {
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

private fun getCityDescription(city: String): String {
    return when (city) {
        "Yerevan" -> "The capital and largest city of Armenia."
        "Washington" -> "The capital city of the United States."
        "Madrid" -> "The capital and largest city of Spain."
        "Paris" -> "The capital and largest city of France, known for its art, fashion, and culture."
        "Tokyo" -> "The capital and largest city of Japan, known for its technology and pop culture."
        "Warsaw" -> "The capital and largest city of Poland."
        else -> "Description not available."
    }
}
@Composable
fun CityItem(city: String, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onItemClick)
    ) {
        Text(
            text = city,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileHW2Theme {
    }
}