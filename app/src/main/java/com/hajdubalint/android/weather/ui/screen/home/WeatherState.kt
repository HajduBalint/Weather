package com.hajdubalint.android.weather.ui.screen.home

import com.hajdubalint.android.weather.utils.model.WeatherDetails

sealed interface WeatherState {
    data class Success(val weatherDetails: WeatherDetails) : WeatherState
    object Error : WeatherState
    object Loading : WeatherState
}