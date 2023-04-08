package com.dlgsoft.sweetweather.di

import android.app.Application
import com.dlgsoft.sweetweather.data.remote.WeatherApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().also {
      it.setLevel(HttpLoggingInterceptor.Level.BASIC)
    }
    return OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .build()
  }

  @Provides
  @Singleton
  fun provideWeatherApi(
    okHttpClient: OkHttpClient
  ): WeatherApi {
    return Retrofit.Builder()
      .baseUrl("https://api.open-meteo.com/")
      .client(okHttpClient)
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
      .create()
  }

  @Provides
  @Singleton
  fun provideFusedLocationProvider(application: Application): FusedLocationProviderClient {
    return LocationServices.getFusedLocationProviderClient(application)
  }
}