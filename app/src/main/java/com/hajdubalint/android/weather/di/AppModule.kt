package com.hajdubalint.android.weather.di

import android.app.Application
import androidx.room.Room
import com.hajdubalint.android.weather.utils.database.CityDao
import com.hajdubalint.android.weather.utils.database.DataBase
import com.hajdubalint.android.weather.utils.model.base.AppConstant.DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ): DataBase {
        return Room.databaseBuilder(
            app,
            DataBase::class.java,
            DATABASE
        ).apply {
            fallbackToDestructiveMigration()
        }.build()
    }

    @Provides
    @Singleton
    fun provideCityDao(database: DataBase): CityDao =
        database.cityDao()
}