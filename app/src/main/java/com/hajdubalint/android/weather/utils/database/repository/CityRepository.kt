package com.hajdubalint.android.weather.utils.database.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.hajdubalint.android.weather.utils.database.CityDatabase
import com.hajdubalint.android.weather.utils.model.City
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepository
@Inject constructor(

) {

    var database: CityDatabase? = null
    var cities: LiveData<List<City>>? = null

    private fun initializeDB(context: Context): CityDatabase {
        return CityDatabase.getInstance(context)
    }

    fun insertCity(context: Context, city: City) {
        database = initializeDB(context)

        CoroutineScope(IO).launch {
            database!!.cityDao().insert(city)
        }
    }

    fun getAllCities(context: Context): LiveData<List<City>> {
        database = initializeDB(context)

        cities = database!!.cityDao().getAll()

        return cities as LiveData<List<City>>
    }

    fun deleteCity(context: Context, city: City) {
        database = initializeDB(context)

        CoroutineScope(IO).launch {
            database!!.cityDao().delete(city)
        }
    }

    fun deleteAllCities(context: Context) {
        database = initializeDB(context)

        CoroutineScope(IO).launch {
            database!!.cityDao().deleteAll()
        }
    }
}