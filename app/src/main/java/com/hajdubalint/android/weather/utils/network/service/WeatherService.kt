package com.hajdubalint.android.weather.utils.network.service

import com.hajdubalint.android.weather.utils.network.model.response.WeatherResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun getWeatherFromCoordinatesAsync(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("lang") lang: String
    ): Deferred<Response<WeatherResponse>>

    @GET("weather")
    fun getWeatherFromCityNameAsync(
        @Query("q") q: String,
        @Query("lang") lang: String,
    ): Deferred<Response<WeatherResponse>>
}