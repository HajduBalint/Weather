package com.hajdubalint.android.weather.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hajdubalint.android.weather.navigation.Navigation
import com.hajdubalint.android.weather.navigation.NavigationUtil
import com.hajdubalint.android.weather.utils.WeatherResponseConverter
import com.hajdubalint.android.weather.utils.network.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val navigationUtil: NavigationUtil
) : ViewModel() {

    var weatherState: WeatherState by mutableStateOf(WeatherState.Loading)
        private set

    fun getWeatherData(cityName: String?) {
        cityName?.let {
            getWeatherFromCityName(cityName)
        } ?: run {
            getWeatherFromCoordinates()
        }
    }

    private fun getWeatherFromCoordinates() {
        viewModelScope.launch {
            weatherState = try {
                weatherRepository.getWeatherFromCoordinates("47.9026", "20.3733")
                    ?.let { weatherResponse ->
                        WeatherState.Success(
                            WeatherResponseConverter.convertWeatherResponseToWeatherDetails(
                                weatherResponse
                            )
                        )
                    } ?: run {
                    WeatherState.Error
                }
            } catch (e: IOException) {
                WeatherState.Error
            } catch (e: HttpException) {
                WeatherState.Error
            }
        }
    }

    fun onCityListCLicked(): () -> Unit {
        return {
            navigationUtil.navigateTo(Navigation.SEARCH.route)
        }
    }

    fun onTryAgainCLicked(): () -> Unit {
        return {
            getWeatherFromCoordinates()
        }
    }


    private fun getWeatherFromCityName(cityName: String) {
        viewModelScope.launch {
            weatherState = try {
                weatherRepository.getWeatherFromCityName(cityName)
                    ?.let { weatherResponse ->
                        WeatherState.Success(
                            WeatherResponseConverter.convertWeatherResponseToWeatherDetails(
                                weatherResponse
                            )
                        )
                    } ?: run {
                    WeatherState.Error
                }
            } catch (e: IOException) {
                WeatherState.Error
            } catch (e: HttpException) {
                WeatherState.Error
            }
        }
    }
}