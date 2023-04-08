package com.dlgsoft.sweetweather.domain.repository

import com.dlgsoft.sweetweather.domain.util.Resource
import com.dlgsoft.sweetweather.domain.weather.WeatherInfo

interface WeatherRepository {
  suspend fun getWeatherData(lat: Double, lng: Double): Resource<WeatherInfo>
}