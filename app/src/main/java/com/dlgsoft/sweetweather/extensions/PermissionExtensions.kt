package com.dlgsoft.sweetweather.extensions

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

fun ComponentActivity.askForPermissionsIfNotGranted(
  permissions: List<String>,
  onPermissionsGranted: () -> Unit
) {
  val hasPermissionsGranted = permissions.all { checkIfPermissionGranted(it) }
  if (!hasPermissionsGranted) {
    registerForActivityResult(
      ActivityResultContracts.RequestMultiplePermissions()
    ) {
      onPermissionsGranted()
    }.run {
      launch(
        permissions.toTypedArray()
      )
    }
  }
}

fun Context.checkIfPermissionGranted(permission: String): Boolean =
  ContextCompat.checkSelfPermission(
    this,
    permission
  ) == PackageManager.PERMISSION_GRANTED