package com.dlgsoft.sweetweather.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dlgsoft.sweetweather.presentation.screens.home.HomeScreen
import com.dlgsoft.sweetweather.presentation.screens.home.HomeViewModel
import com.dlgsoft.sweetweather.presentation.screens.settings.SettingsScreen

@Composable
fun NavigationGraph(
  homeViewModel: HomeViewModel = hiltViewModel(),
  navController: NavHostController
) {
  NavHost(navController, startDestination = BottomNavigationItem.Home.route) {
    composable(BottomNavigationItem.Home.route) {
      HomeScreen(homeViewModel)
    }
    composable(BottomNavigationItem.Settings.route) {
      SettingsScreen()
    }
  }
}