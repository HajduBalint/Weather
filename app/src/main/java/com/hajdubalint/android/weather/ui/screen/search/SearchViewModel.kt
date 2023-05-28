package com.hajdubalint.android.weather.ui.screen.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hajdubalint.android.weather.utils.database.repository.CityRepository
import com.hajdubalint.android.weather.utils.model.City
import com.hajdubalint.android.weather.utils.network.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
@Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val cityRepository: CityRepository
) : ViewModel() {
    fun getWeatherFromCityName() {
        viewModelScope.launch {
            weatherRepository.getWeatherFromCityName("Eger")?.let {
                Log.d("TAG from name", it.toString())
            } ?: run {

            }
        }
    }

    fun insertCity(city: City) {
        viewModelScope.launch {
            cityRepository.insertCity(city)
        }
    }

    fun getAllCity() {
        viewModelScope.launch {
            cityRepository.getAllCities().let {
                Log.d("TAG get all cities", it.toString())
            }
        }
    }

    fun deleteCity(city: City) {
        viewModelScope.launch {
            cityRepository.deleteCity(city)
        }
    }

    fun deleteAllCities() {
        viewModelScope.launch {
            cityRepository.deleteAllCities()
        }
    }
}