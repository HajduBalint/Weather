package com.hajdubalint.android.weather.utils.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hajdubalint.android.weather.utils.model.City

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class CityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        private var INSTANCE: CityDatabase? = null

        fun getInstance(context: Context): CityDatabase {
            return if (INSTANCE == null) {
                Room.databaseBuilder(context, CityDatabase::class.java, "city_database").build()
            } else {
                INSTANCE!!
            }
        }
    }
}