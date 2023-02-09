package com.app.weather

import android.app.Application
import com.app.weather.Repository.WeatherRepository
import com.app.weather.data.api.ApiService
import com.app.weather.data.api.RetrofitHelper
import com.app.weather.data.db.WeatherDatabase

class WeatherApplication:Application() {
    lateinit var repository: WeatherRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val service = RetrofitHelper.getInstance().create(ApiService::class.java)
        var database =  WeatherDatabase.getDatabase(applicationContext)
         repository = WeatherRepository(service,database,applicationContext)

    }
}