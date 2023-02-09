package com.app.weather.data.api

import com.app.weather.response.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/data/2.5/weather")
    suspend fun getWeather(@Query("lat") lat:String, @Query("lon") lon:String,@Query("appid") appid:String="ca25866d0df3dbcb7f4f6e38edfdeba1",@Query("units") units:String = "metric"): Response<WeatherData>?
}