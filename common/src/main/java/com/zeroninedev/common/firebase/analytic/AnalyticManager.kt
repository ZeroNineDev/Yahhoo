package com.zeroninedev.common.firebase.analytic

/**
 * Manager for working with FirebaseAnalytic
 *
 */
interface AnalyticManager {

    /**
     * Send event to analytic
     *
     * @param eventName event name
     * @param params event params
     */
    fun sendEvent(eventName: String, params: Map<String, String>)
}