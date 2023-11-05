package com.example.mobile_hw2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                    composable("list_screen") {
                        ListScreen(navController)
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

            Button(
                onClick = { navController.navigate("list_screen") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.list_button))
            }
        }
    }
}

@Composable
fun ListScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(cityList) { city ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                CityName(city = city)
            }
            Image(
                painter = painterResource(id = getCityImage(city)),
                contentDescription = "$city Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = CityDescription(city),
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        item {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.back_button))
            }
        }
    }
}


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

@Composable
fun CityDescription(city: String): String {
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

@Preview(showBackground = true)
@Composable
fun CityPreview() {
    MobileHW2Theme {
    }
}