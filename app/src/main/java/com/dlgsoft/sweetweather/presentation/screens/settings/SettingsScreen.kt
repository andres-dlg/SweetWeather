package com.dlgsoft.sweetweather.presentation.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen() {

  Box(
    modifier = Modifier
      .fillMaxSize()
  ) {
    Text(text = "Settings screen")
  }
}
