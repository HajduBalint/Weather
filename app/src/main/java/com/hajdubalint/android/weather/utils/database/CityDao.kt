package com.hajdubalint.android.weather.utils.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hajdubalint.android.weather.utils.model.City


@Dao
interface CityDao {
    @Query("SELECT * FROM city")
    fun getAll(): LiveData<List<City>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(city: City)

    @Delete
    fun delete(city: City)

    @Query("DELETE FROM city")
    fun deleteAll()
}