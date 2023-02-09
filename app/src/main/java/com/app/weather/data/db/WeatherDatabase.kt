package com.app.weather.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.weather.Utils.DataConverter
import com.app.weather.response.WeatherData

@Database(entities = [WeatherData::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class WeatherDatabase:RoomDatabase() {
    abstract fun weatherDao():WeatherDao

    companion object{
        @Volatile
        private var INSTANCE:WeatherDatabase?= null

        fun getDatabase(context: Context):WeatherDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,WeatherDatabase::class.java,"weatherdb").build()
                }
            }
            return INSTANCE!!
        }
    }
}