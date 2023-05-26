package com.hajdubalint.android.weather.utils.network.model.response

import com.hajdubalint.android.weather.utils.model.*

data class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: String,
    val wind: Wind,
    val clouds: Clouds,
    val dt: String,
    val sys: Sys,
    val timezone: String,
    val id: String,
    val name: String,
    val cod: String
)