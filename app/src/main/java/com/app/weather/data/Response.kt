package com.app.weather.data


sealed class Response<T>(val data: T? = null,val err:String? = null){
    class Loading<T> :Response<T>()
    class Success<T>(data: T?=null):Response<T>(data = data)
    class Error<T>(err:String):Response<T>(err = err)
}