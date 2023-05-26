package com.hajdubalint.android.weather.utils.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo val name: String
)