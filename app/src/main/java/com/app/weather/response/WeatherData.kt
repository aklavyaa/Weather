package com.app.weather.response


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "weather_data")
data class WeatherData(

    @PrimaryKey(autoGenerate = true)
    val weatherId:Int,

    @SerializedName("base")
    val base: String,

    @SerializedName("cod")
    val cod: Int,

    @Embedded
    @SerializedName("coord")
    val coord: Coord?,

    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,

    @Embedded
    @SerializedName("main")
    val main: Main?,

    @SerializedName("name")
    val name: String,

    @Embedded
    @SerializedName("sys")
    val sys: Sys?,

    @SerializedName("timezone")
    val timezone: Int,

    @SerializedName("visibility")
    val visibility: Int,

    @SerializedName("weather")
    val weather: List<Weather>?,

    @Embedded
    @SerializedName("wind")
    val wind: Wind?
){
//    constructor():this(0,"",0,null,0,0,null,"",null,0,0,null,null)
}
