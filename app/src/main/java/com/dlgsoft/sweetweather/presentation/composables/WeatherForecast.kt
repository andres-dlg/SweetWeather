package com.dlgsoft.sweetweather.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dlgsoft.sweetweather.presentation.WeatherState

@Composable
fun WeatherForecast(
  state: WeatherState,
  modifier: Modifier = Modifier
) {
  state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->
    Column(
      modifier = modifier
    ) {
      Text(
        text = "Today",
        fontSize = 20.sp,
        color = Color.White,
        modifier = Modifier.padding(horizontal = 16.dp)
      )
      Spacer(modifier = Modifier.height(16.dp))
      LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        content = {
          items(data) { weatherData ->
            HourlyWeatherDisplay(
              weatherData = weatherData,
              modifier = Modifier
                .height(100.dp)
                .padding(horizontal = 16.dp)
            )
          }
        }
      )
    }
  }
}