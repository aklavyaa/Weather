package com.app.weather.response


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("speed")
    val speed: Double
)