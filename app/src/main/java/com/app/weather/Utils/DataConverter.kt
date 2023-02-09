package com.app.weather.Utils

import androidx.room.TypeConverter
import com.app.weather.response.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun fromCountryLangList(value: List<Weather>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Weather>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<Weather> {
        val gson = Gson()
        val type = object : TypeToken<List<Weather>>() {}.type
        return gson.fromJson(value, type)
    }
}