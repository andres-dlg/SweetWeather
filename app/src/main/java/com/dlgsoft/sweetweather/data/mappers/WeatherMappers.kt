package com.dlgsoft.sweetweather.data.mappers

import com.dlgsoft.sweetweather.data.remote.WeatherDataDto
import com.dlgsoft.sweetweather.data.remote.WeatherDto
import com.dlgsoft.sweetweather.domain.weather.WeatherData
import com.dlgsoft.sweetweather.domain.weather.WeatherDataPerDay
import com.dlgsoft.sweetweather.domain.weather.WeatherInfo
import com.dlgsoft.sweetweather.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

private data class IndexedWeatherData(
  val index: Int,
  val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): List<WeatherDataPerDay> {
  return time.mapIndexed { index, time ->
    val temperature = temperatures[index]
    val weatherCode = weatherCodes[index]
    val windSpeed = windSpeeds[index]
    val pressure = pressures[index]
    val humidity = humidities[index]
    IndexedWeatherData(
      index = index,
      data = WeatherData(
        time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
        temperatureCelsius = temperature,
        pressure = pressure,
        windSpeed = windSpeed,
        humidity = humidity,
        weatherType = WeatherType.fromWMO(weatherCode)
      )
    )
  }.groupBy {
    it.index / 24
  }.mapValues { value ->
    value.value.map { it.data }
  }.map {
    WeatherDataPerDay(
      index = it.key,
      dateName = getDayName(it.key),
      weatherData = it.value
    )
  }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
  val weatherDataMap = weatherData.toWeatherDataMap()
  val now = LocalDateTime.now()
  val currentWeatherData = weatherDataMap[0].weatherData.find {
    val hour = if (now.minute < 30) now.hour else now.hour + 1
    it.time.hour == hour
  }
  return WeatherInfo(
    weatherDataPerDay = weatherDataMap,
    currentWeatherData = currentWeatherData
  )
}

private fun getDayName(index: Int) = when (index) {
  0 -> "Today"
  1 -> "Tomorrow"
  else -> {
    LocalDateTime.now().plusDays(index.toLong()).dayOfWeek.name.lowercase().replaceFirstChar {
      if (it.isLowerCase()) it.titlecase(
        Locale.ENGLISH
      ) else it.toString()
    }
  }
}