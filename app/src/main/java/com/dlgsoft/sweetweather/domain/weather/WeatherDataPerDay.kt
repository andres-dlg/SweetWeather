package com.dlgsoft.sweetweather.domain.weather

data class WeatherDataPerDay(
  val index: Int,
  val dateName: String,
  val weatherData: List<WeatherData>
)
