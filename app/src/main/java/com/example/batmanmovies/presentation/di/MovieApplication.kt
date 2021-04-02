package com.example.batmanmovies.presentation.di

import android.app.Application
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApplication : MultiDexApplication()