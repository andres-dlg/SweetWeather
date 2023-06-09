package com.dlgsoft.sweetweather.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dlgsoft.sweetweather.domain.location.LocationTracker
import com.dlgsoft.sweetweather.domain.repository.WeatherRepository
import com.dlgsoft.sweetweather.domain.util.Resource
import com.dlgsoft.sweetweather.presentation.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val repository: WeatherRepository,
  private val locationTracker: LocationTracker
): ViewModel() {

  var state by mutableStateOf(WeatherState())
    private set

  init {
    loadWeatherInfo()
  }

  fun loadWeatherInfo() {
    viewModelScope.launch {
      state = state.copy(
        isLoading = true,
        error = null
      )
      locationTracker.getCurrentLocation()?.let { location ->
        when (val result = repository.getWeatherData(location.latitude, location.latitude)) {
          is Resource.Error -> {
            state = state.copy(
              weatherInfo = null,
              isLoading = false,
              error = result.message
            )
          }
          is Resource.Success -> {
            state = state.copy(
              weatherInfo = result.data,
              isLoading = false,
              error = null
            )
          }
        }
      } ?: kotlin.run {
        state = state.copy(
          weatherInfo = null,
          isLoading = false,
          error = "Couldn't retrieve location. Make sure to grant permission and enable GPS"
        )
      }
    }
  }
}