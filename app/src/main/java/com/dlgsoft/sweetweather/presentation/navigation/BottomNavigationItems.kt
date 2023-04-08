package com.dlgsoft.sweetweather.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dlgsoft.sweetweather.R

sealed class BottomNavigationItem(
  @StringRes val titleResId: Int,
  @DrawableRes val iconResId: Int,
  val route: String
) {

  object Home: BottomNavigationItem(
    R.string.bottom_nav_home,
    R.drawable.ic_home,
    "home"
  )

  object Settings: BottomNavigationItem(
    R.string.bottom_nav_settings,
    R.drawable.ic_settings,
    "settings"
  )
}