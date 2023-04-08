package com.dlgsoft.sweetweather.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dlgsoft.sweetweather.presentation.WeatherState

@Composable
fun WeatherForecast(
  state: WeatherState,
  itemBackgroundColor: Color,
  modifier: Modifier = Modifier
) {
  state.weatherInfo?.weatherDataPerDay?.let { daysWithWeatherData ->
    LazyColumn(
      modifier = modifier,
      contentPadding = PaddingValues(bottom = 32.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(daysWithWeatherData) {
        WeatherDailyCard(it, itemBackgroundColor)
      }
    }
  }
}