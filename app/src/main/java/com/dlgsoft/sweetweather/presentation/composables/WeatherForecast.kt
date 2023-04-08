package com.dlgsoft.sweetweather.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
  backgroundColor: Color,
  modifier: Modifier = Modifier
) {

  state.weatherInfo?.weatherDataPerDay?.let { daysWithWeatherData ->
    LazyColumn(
      modifier = modifier,
      contentPadding = PaddingValues(bottom = 32.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(daysWithWeatherData) {
        Card(
          backgroundColor = backgroundColor,
          shape = RoundedCornerShape(10.dp),
          modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
          elevation = 8.dp
        ) {
          Column(
            modifier = Modifier.padding(vertical = 16.dp)
          ) {
            Text(
              text = it.dateName,
              fontSize = 20.sp,
              color = Color.White,
              modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
              contentPadding = PaddingValues(horizontal = 16.dp),
              horizontalArrangement = Arrangement.spacedBy(16.dp),
              content = {
                items(it.weatherData) { weatherDataItem ->
                  HourlyWeatherDisplay(
                    weatherData = weatherDataItem,
                    modifier = Modifier
                      .height(100.dp)
                  )
                }
              }
            )
          }
        }
      }
    }
  }
}