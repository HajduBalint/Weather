package com.hajdubalint.android.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.hajdubalint.android.weather.ui.screen.home.HomeViewModel
import com.hajdubalint.android.weather.ui.screen.search.SearchViewModel
import com.hajdubalint.android.weather.ui.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val hv: HomeViewModel by viewModels()

    val sv: SearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                hv.getWeatherFromCoordinates()

                sv.getWeatherFromCityName()

                sv.getAllCity()
            }
        }
    }
}