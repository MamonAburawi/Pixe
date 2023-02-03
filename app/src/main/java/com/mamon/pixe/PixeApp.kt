package com.mamon.pixe

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PixeApp: Application() {
    override fun onCreate() {
        super.onCreate()

    }
}