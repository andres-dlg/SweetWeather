package com.dlgsoft.sweetweather.presentation.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dlgsoft.sweetweather.presentation.WeatherViewModel
import com.dlgsoft.sweetweather.presentation.composables.WeatherCard
import com.dlgsoft.sweetweather.presentation.composables.WeatherForecast
import com.dlgsoft.sweetweather.presentation.ui.theme.Purple500
import com.dlgsoft.sweetweather.presentation.ui.theme.Purple700
import com.dlgsoft.sweetweather.presentation.ui.theme.SweetWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

  private val viewModel: WeatherViewModel by viewModels()

  private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    permissionLauncher = registerForActivityResult(
      ActivityResultContracts.RequestMultiplePermissions()
    ) {
      viewModel.loadWeatherInfo()
    }
    permissionLauncher.launch(
      arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
      )
    )
    setContent {
      SweetWeatherTheme {
        Box(modifier = Modifier.fillMaxSize()) {
          Column(
            modifier = Modifier
              .fillMaxSize()
              .background(Purple500),
          ) {
            WeatherCard(
              state = viewModel.state,
              backgroundColor = Purple700
            )
            WeatherForecast(
              state = viewModel.state,
              backgroundColor = Purple700,
              modifier = Modifier.fillMaxWidth()
            )
          }
          if (viewModel.state.isLoading) {
            CircularProgressIndicator(
              modifier = Modifier.align(Alignment.Center),
              color = Purple700
            )
          }
          viewModel.state.error?.let { error ->
            Text(
              text = error,
              color = Color.Red,
              textAlign = TextAlign.Center,
              modifier = Modifier.align(Alignment.Center)
            )
          }
        }
      }
    }
  }
}