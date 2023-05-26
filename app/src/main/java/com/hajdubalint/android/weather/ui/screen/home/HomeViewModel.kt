package com.hajdubalint.android.weather.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hajdubalint.android.weather.utils.network.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    fun getWeatherFromCoordinates() {
        viewModelScope.launch {
            weatherRepository.getWeatherFromCoordinates("47.9026", "20.3733")?.let {
                Log.d("TAG from coord", it.toString())
            } ?: run {

            }
        }
    }
}