package com.dlgsoft.sweetweather.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.dlgsoft.sweetweather.presentation.composables.WeatherCard
import com.dlgsoft.sweetweather.presentation.composables.WeatherForecast
import com.dlgsoft.sweetweather.presentation.ui.theme.Purple500
import com.dlgsoft.sweetweather.presentation.ui.theme.Purple700

@Composable
fun HomeScreen(
  homeViewModel: HomeViewModel
) {
  Box(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(Purple500),
    ) {
      WeatherCard(
        state = homeViewModel.state,
        backgroundColor = Purple700
      )
      WeatherForecast(
        state = homeViewModel.state,
        itemBackgroundColor = Purple700,
        modifier = Modifier.fillMaxWidth()
      )
    }
    if (homeViewModel.state.isLoading) {
      CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center),
        color = Purple700
      )
    }
    homeViewModel.state.error?.let { error ->
      Text(
        text = error,
        color = Color.Red,
        textAlign = TextAlign.Center,
        modifier = Modifier.align(Alignment.Center)
      )
    }
  }
}