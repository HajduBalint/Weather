package com.hajdubalint.android.weather.utils.model

data class WeatherDetails(
    val cityName: String,
    val temperature: String,
    val description: String,
    val maxTemperature: String,
    val minTemperature: String,
    val weatherInfoItems: List<WeatherInfoItem>
)
