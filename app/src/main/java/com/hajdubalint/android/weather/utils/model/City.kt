package com.hajdubalint.android.weather.utils.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val name: String
)