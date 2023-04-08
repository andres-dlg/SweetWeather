package com.dlgsoft.sweetweather.presentation.ui

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.dlgsoft.sweetweather.extensions.askForPermissionsIfNotGranted
import com.dlgsoft.sweetweather.presentation.screens.HomeScreen
import com.dlgsoft.sweetweather.presentation.screens.HomeViewModel
import com.dlgsoft.sweetweather.presentation.ui.theme.SweetWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

  private val homeViewModel: HomeViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    askForPermissionsIfNotGranted(
      listOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION)
    ) {
      homeViewModel.loadWeatherInfo()
    }
    setContent {
      SweetWeatherTheme {
        HomeScreen(homeViewModel = homeViewModel)
      }
    }
  }
}