package com.dlgsoft.sweetweather.di

import com.dlgsoft.sweetweather.data.location.DefaultLocationTracker
import com.dlgsoft.sweetweather.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

  @Binds
  @Singleton
  abstract fun bindLocationTracker(
    defaultLocationTracker: DefaultLocationTracker
  ): LocationTracker
}