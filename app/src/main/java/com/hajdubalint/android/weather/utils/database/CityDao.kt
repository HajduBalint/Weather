package com.hajdubalint.android.weather.utils.database

import androidx.room.*
import com.hajdubalint.android.weather.utils.model.City


@Dao
interface CityDao {
    @Query("SELECT * FROM city")
    suspend fun getAll(): List<City>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(city: City)

    @Delete
    suspend fun delete(city: City)

    @Query("DELETE FROM city")
    suspend fun deleteAll()
}