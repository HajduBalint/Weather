package com.hajdubalint.android.weather.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hajdubalint.android.weather.navigation.Navigation
import com.hajdubalint.android.weather.navigation.NavigationUtil
import com.hajdubalint.android.weather.utils.database.repository.CityRepository
import com.hajdubalint.android.weather.utils.model.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
@Inject constructor(
    private val cityRepository: CityRepository,
    private val navigationUtil: NavigationUtil
) : ViewModel() {

    val cityList: MutableStateFlow<List<City>?> = MutableStateFlow(null)

    init {
        getAllCity()
    }

    fun insertCity(): (String) -> Unit {
        return { cityName ->
            viewModelScope.launch {
                cityRepository.insertCity(City(name = cityName))
                getAllCity()
            }
        }
    }

    private fun getAllCity() {
        viewModelScope.launch {
            cityList.value = cityRepository.getAllCities()
        }
    }

    fun deleteCity(): (City) -> Unit {
        return {
            viewModelScope.launch {
                cityRepository.deleteCity(it)
                getAllCity()
            }
        }
    }

    fun onCityClicked(): (String) -> Unit {
        return { cityName ->
            navigationUtil.navigateTo("${Navigation.HOME.route}/$cityName")
        }
    }

    fun onBackClicked(): () -> Unit {
        return {
            navigationUtil.navigateTo(Navigation.HOME.route)
        }
    }
}