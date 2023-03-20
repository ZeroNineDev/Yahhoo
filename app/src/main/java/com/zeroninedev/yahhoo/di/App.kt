package com.zeroninedev.yahhoo.di

import android.app.Application
import android.os.Build
import com.zeroninedev.common.firebase.analytic.AnalyticManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
internal class App : Application() {

    @Inject
    lateinit var analytics: AnalyticManager

    override fun onCreate() {
        super.onCreate()
        analytics.sendEvent(
            "start_app", mapOf(
                "model" to Build.MODEL,
                "device" to Build.DEVICE,
                "android_version" to Build.VERSION.SDK_INT.toString()
            )
        )
    }
}