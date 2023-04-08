package com.dlgsoft.sweetweather.data.repository

import com.dlgsoft.sweetweather.data.mappers.toWeatherInfo
import com.dlgsoft.sweetweather.data.remote.WeatherApi
import com.dlgsoft.sweetweather.domain.repository.WeatherRepository
import com.dlgsoft.sweetweather.domain.util.Resource
import com.dlgsoft.sweetweather.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
  private val api: WeatherApi
): WeatherRepository {
  override suspend fun getWeatherData(lat: Double, lng: Double): Resource<WeatherInfo> {
    return try {
      Resource.Success(
        data = api.getWeatherData(
          lat, lng
        ).toWeatherInfo()
      )
    } catch (e: Exception) {
      e.printStackTrace()
      Resource.Error(e.message ?: "An unknown error occurred.")
    }
  }
}