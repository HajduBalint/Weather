package com.hajdubalint.android.weather.utils.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hajdubalint.android.weather.utils.model.City

@Database(
    entities = [City::class],
    version = 1,
    exportSchema = false
)

abstract class DataBase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}