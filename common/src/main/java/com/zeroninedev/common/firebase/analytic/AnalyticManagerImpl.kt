package com.zeroninedev.common.firebase.analytic

import android.util.Log
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Singleton

@Singleton
internal class AnalyticManagerImpl(
    private val firebaseAnalytics: FirebaseAnalytics
) : AnalyticManager {

    override fun sendEvent(eventName: String, params: Map<String, String>) {
        val bundle = bundleOf()
        params.forEach { bundle.putString(it.key, it.value) }
        Log.d(LOG, "Send event $bundle")
        firebaseAnalytics.logEvent(eventName, bundle)
    }

    private companion object {

        const val LOG = "AnalyticManager"
    }
}