package com.hajdubalint.android.weather.utils.network.repository

import com.hajdubalint.android.weather.utils.network.ApiFactory.api
import com.hajdubalint.android.weather.utils.network.model.response.WeatherResponse
import retrofit2.Response

class WeatherRepository {

    suspend fun getWeatherFromCoordinates(
        lat: String,
        lon: String,
        lang: String,
    ): WeatherResponse? {
        return apiCall {
            api.getWeatherFromCoordinatesAsync(lat, lon, lang).await()
        }
    }

    suspend fun getWeatherFromCityName(
        q: String,
        lang: String,
    ): WeatherResponse? {
        return apiCall { api.getWeatherFromCityNameAsync(q, lang).await() }
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