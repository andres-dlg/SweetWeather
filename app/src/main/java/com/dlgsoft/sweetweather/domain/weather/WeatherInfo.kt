package com.dlgsoft.sweetweather.domain.weather

data class WeatherInfo(
  val weatherDataPerDay: List<WeatherDataPerDay>,
  val currentWeatherData: WeatherData?
)