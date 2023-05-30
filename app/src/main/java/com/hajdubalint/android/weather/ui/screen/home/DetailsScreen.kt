package com.hajdubalint.android.weather.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hajdubalint.android.weather.ui.custom.WeatherInfoItem
import com.hajdubalint.android.weather.utils.model.WeatherDetails


@Composable
fun DetailsScreen(weatherDetails: WeatherDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
        ) {
            item(
                span = { GridItemSpan(2) }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        text = weatherDetails.cityName,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = weatherDetails.temperature,
                        fontSize = 64.sp
                    )

                    Text(
                        text = weatherDetails.description,
                        fontSize = 24.sp
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = weatherDetails.maxTemperature,
                            fontSize = 24.sp
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 4.dp),
                            text = weatherDetails.minTemperature,
                            fontSize = 24.sp
                        )
                    }
                }
            }
            items(weatherDetails.weatherInfoItems) { weatherInfo ->
                WeatherInfoItem(weatherInfo)
            }
        }
    }
}