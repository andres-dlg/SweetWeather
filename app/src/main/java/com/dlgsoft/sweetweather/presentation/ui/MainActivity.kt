package com.dlgsoft.sweetweather.presentation.ui

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dlgsoft.sweetweather.extensions.askForPermissionsIfNotGranted
import com.dlgsoft.sweetweather.presentation.navigation.BottomNavigation
import com.dlgsoft.sweetweather.presentation.navigation.NavigationGraph
import com.dlgsoft.sweetweather.presentation.screens.home.HomeViewModel
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
        val navController = rememberNavController()
        Scaffold(
          bottomBar = { BottomNavigation(navController = navController) }
        ) {
          Box(modifier = Modifier.padding(it)) {
            NavigationGraph(navController = navController)
          }
        }
        //HomeScreen(homeViewModel = homeViewModel)
      }
    }
  }
}