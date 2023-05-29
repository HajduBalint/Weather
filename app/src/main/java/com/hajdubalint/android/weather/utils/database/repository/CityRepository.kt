package com.hajdubalint.android.weather.utils.database.repository

import com.hajdubalint.android.weather.utils.database.CityDao
import com.hajdubalint.android.weather.utils.model.City
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepository
@Inject constructor(
    private val cityDao: CityDao
) {
    suspend fun insertCity(city: City) {
        CoroutineScope(IO).launch {
            cityDao.insert(city)
        }
    }

    suspend fun getAllCities(): List<City> {
        return cityDao.getAll()
    }

    suspend fun deleteCity(city: City) {
        CoroutineScope(IO).launch {
            cityDao.delete(city)
        }
    }

    suspend fun deleteAllCities() {
        CoroutineScope(IO).launch {
            cityDao.deleteAll()
        }
    }
}