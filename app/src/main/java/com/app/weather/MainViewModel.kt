package com.app.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.weather.Repository.WeatherRepository
import com.app.weather.data.Response
import com.app.weather.response.WeatherData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: WeatherRepository):ViewModel() {
    init {

        viewModelScope.launch(Dispatchers.IO) {
            repository.getWeatherData("45.508888","-73.561668")
        }
    }


    val weather:LiveData<Response<WeatherData>>
    get() = repository.weather
}