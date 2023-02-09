package com.app.weather.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.weather.Utils.NetworkUtils
import com.app.weather.data.Response
import com.app.weather.data.api.ApiService
import com.app.weather.data.db.WeatherDatabase
import com.app.weather.response.WeatherData

class WeatherRepository(
    private val apiService: ApiService,
    private val db: WeatherDatabase,
    private val applicationContext: Context
) {

    private val weatherLiveData = MutableLiveData<Response<WeatherData>>()
    val weather: LiveData<Response<WeatherData>>
    get() = weatherLiveData
   suspend fun getWeatherData(lat:String ,lon:String){
       if (!NetworkUtils.isNetworkAvailable(applicationContext)){
            weatherLiveData.postValue(Response.Error("No Internet Connection"))
           return
       }
      var isDBEmpty =  db.weatherDao().getWeather()==null
       if (isDBEmpty){
           try {
               val result =   apiService.getWeather(lat,lon)
               if (result?.body()!=null){
                   db.weatherDao().saveWeather(result.body() as WeatherData)
                   weatherLiveData.postValue(Response.Success(result.body()))
               } else {
                   weatherLiveData.postValue(Response.Error("Something went wrong"))

               }
           }catch (e:Exception){
                weatherLiveData.postValue(Response.Error(e.message.toString()))
           }

       } else {
            weatherLiveData.postValue(Response.Success(db.weatherDao().getWeather()))
       }

    }
}