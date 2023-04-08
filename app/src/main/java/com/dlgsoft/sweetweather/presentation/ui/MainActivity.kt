package com.dlgsoft.sweetweather.presentation.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.dlgsoft.sweetweather.presentation.WeatherViewModel
import com.dlgsoft.sweetweather.presentation.screens.HomeScreen
import com.dlgsoft.sweetweather.presentation.ui.theme.SweetWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

  private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

  private val viewModel: WeatherViewModel by viewModels()

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
        HomeScreen(viewModel = viewModel)
      }
    }
  }
}