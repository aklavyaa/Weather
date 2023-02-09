package com.app.weather.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.weather.response.WeatherData

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWeather(profile:WeatherData)

    @Query(value = "SELECT * FROM weather_data")
    suspend fun getWeather():WeatherData?
}