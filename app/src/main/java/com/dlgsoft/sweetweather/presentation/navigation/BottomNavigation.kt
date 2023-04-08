package com.dlgsoft.sweetweather.presentation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dlgsoft.sweetweather.presentation.ui.theme.Purple700

@Composable
fun BottomNavigation(navController: NavController) {
  val items = listOf(
    BottomNavigationItem.Home,
    BottomNavigationItem.Settings,
  )
  BottomNavigation(
    backgroundColor = Purple700,
  ) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    items.forEach { item ->
      BottomNavigationItem(
        icon = {
          Icon(
            painterResource(id = item.iconResId),
            contentDescription = stringResource(id = item.titleResId)
          )
        },
        label = {
          Text(
            text = stringResource(id = item.titleResId),
            fontSize = 9.sp
          )
        },
        alwaysShowLabel = true,
        selected = currentRoute == item.route,
        onClick = {
          navController.navigate(item.route) {
            navController.graph.startDestinationRoute?.let { route ->
              popUpTo(route) {
                saveState = true
              }
            }
            launchSingleTop = true
            restoreState = true
          }
        }
      )
    }
  }
}