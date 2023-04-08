package com.dlgsoft.sweetweather.presentation

import com.dlgsoft.sweetweather.domain.weather.WeatherInfo

data class WeatherState(
  val weatherInfo: WeatherInfo? = null,
  val isLoading: Boolean = false,
  val error: String? = null
)