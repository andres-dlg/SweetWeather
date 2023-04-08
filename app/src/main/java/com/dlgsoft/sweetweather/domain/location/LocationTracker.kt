package com.dlgsoft.sweetweather.domain.location

interface LocationTracker {
  suspend fun getCurrentLocation(): LocationData?
}