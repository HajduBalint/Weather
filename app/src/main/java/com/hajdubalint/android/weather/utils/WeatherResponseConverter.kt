package com.hajdubalint.android.weather.utils

import com.hajdubalint.android.weather.utils.model.*
import com.hajdubalint.android.weather.utils.network.model.response.WeatherResponse
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

object WeatherResponseConverter {

    fun convertWeatherResponseToWeatherDetails(weatherResponse: WeatherResponse): WeatherDetails {
        val cityName = weatherResponse.name
        val weatherDescription: String = weatherResponse.weather[0].description.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }
        val temperature = "${weatherResponse.main.temp.toDouble().roundToInt()}°"
        val maxTemperature = "H: ${weatherResponse.main.temp_max.toDouble().roundToInt()}°"
        val minTemperature = "L: ${weatherResponse.main.temp_min.toDouble().roundToInt()}°"

        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("HH:mm", Locale.ROOT)
        calendar.timeInMillis = weatherResponse.sys.sunrise.toLong() * 1000
        val sunrise: String = sdf.format(calendar.time)
        calendar.timeInMillis = weatherResponse.sys.sunset.toLong() * 1000
        val sunset: String = sdf.format(calendar.time)

        val humidity = "${weatherResponse.main.humidity}%"
        val pressure = "${weatherResponse.main.pressure} hPa"

        val windSpeed = "${weatherResponse.wind.speed} km/h"
        val windDirection: String = windDegreeCalculator(weatherResponse.wind.deg.toDouble())

        val visibility = "${weatherResponse.visibility.toInt() / 1000} km"
        val timeZone: String = timeZoneCalculator(weatherResponse.timezone.toInt())

        val longitude: String = weatherResponse.coord.lon
        val latitude: String = weatherResponse.coord.lat

        return WeatherDetails(
            cityName,
            temperature,
            weatherDescription,
            maxTemperature,
            minTemperature,
            listOf(
                WeatherInfoItem(
                    title = "Sunrise",
                    value = sunrise
                ),
                WeatherInfoItem(
                    title = "Sunset",
                    value = sunset
                ),
                WeatherInfoItem(
                    title = "Humidity",
                    value = humidity
                ),
                WeatherInfoItem(
                    title = "Pressure",
                    value = pressure
                ),
                WeatherInfoItem(
                    title = "Wind speed",
                    value = windSpeed
                ),
                WeatherInfoItem(
                    title = "Wind direction",
                    value = windDirection
                ),
                WeatherInfoItem(
                    title = "Visibility",
                    value = visibility
                ),
                WeatherInfoItem(
                    title = "Time zone",
                    value = timeZone
                ),
                WeatherInfoItem(
                    title = "Longitude",
                    value = longitude
                ),
                WeatherInfoItem(
                    title = "Latitude",
                    value = latitude
                ),
            )
        )
    }

    private fun timeZoneCalculator(zone: Int): String {
        val plusOrMinus =
            if (zone < 0) {
                "-"
            } else {
                "+"
            }

        val time = zone.toDouble() / 3600.0

        var hours = time.toInt()
        if (hours < 0) {
            hours *= -1
        }

        var minute = ((time * 60) % 60).toInt()
        if (minute < 0) {
            minute *= -1
        }
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("HH:mm", Locale.ROOT)
        calendar.set(2020, 1, 1, hours, minute)
        val gmtHours = sdf.format(calendar.time)
        return "GMT ${plusOrMinus}${gmtHours}"
    }

    private fun windDegreeCalculator(degree: Double): String {
        return when (degree) {
            in 348.76..11.25 -> "N"
            in 11.26..33.75 -> "NNE"
            in 33.76..56.25 -> "NE"
            in 56.26..78.75 -> "ENE"
            in 78.76..101.25 -> "E"
            in 101.26..123.75 -> "ESE"
            in 123.76..146.25 -> "SE"
            in 146.26..168.75 -> "SSE"
            in 168.76..191.25 -> "S"
            in 191.26..213.75 -> "SSW"
            in 213.76..236.25 -> "SW"
            in 236.26..258.75 -> "WSW"
            in 258.76..281.25 -> "W"
            in 281.26..303.75 -> "WNW"
            in 303.76..326.25 -> "NW"
            in 326.26..348.75 -> "NNW"
            else -> "-"
        }
    }
}

