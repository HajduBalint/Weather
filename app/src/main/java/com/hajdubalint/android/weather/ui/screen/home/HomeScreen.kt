package com.hajdubalint.android.weather.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    cityName: String?,
    viewModel: HomeViewModel = hiltViewModel()
) {
    viewModel.getWeatherData(cityName)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Current weather.")
                },
                actions = {
                    Text(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable(
                                onClick = viewModel.onCityListCLicked()
                            ),
                        text = "Cities"
                    )
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            when (viewModel.weatherState) {
                is WeatherState.Loading -> LoadingScreen()
                is WeatherState.Success -> DetailsScreen((viewModel.weatherState as WeatherState.Success).weatherDetails)
                is WeatherState.Error -> ErrorScreen(errorMessage = "Error during loading data!")
            }
        }

    }

}