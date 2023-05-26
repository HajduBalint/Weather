package com.hajdubalint.android.weather.utils.network.repository

import com.hajdubalint.android.weather.utils.network.ApiFactory.api
import com.hajdubalint.android.weather.utils.network.model.response.WeatherResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository
@Inject constructor(

) {

    suspend fun getWeatherFromCoordinates(
        lat: String,
        lon: String
    ): WeatherResponse? {
        return apiCall {
            api.getWeatherFromCoordinatesAsync(lat, lon).await()
        }
    }

    suspend fun getWeatherFromCityName(
        q: String
    ): WeatherResponse? {
        return apiCall {
            api.getWeatherFromCityNameAsync(q).await()
        }
    }

    private suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): T? {
        val response = call.invoke()
        var data: T? = null
        if (response.isSuccessful) {
            data = response.body()
        }
        return data
    }
}