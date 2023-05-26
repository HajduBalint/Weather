package com.hajdubalint.android.weather.utils.model

data class Main(
    val temp: String,
    val feels_like: String?,
    var temp_min: String,
    var temp_max: String,
    val pressure: String,
    val humidity: String,
    val sea_level: String?,
    val grnd_level: String?
)
