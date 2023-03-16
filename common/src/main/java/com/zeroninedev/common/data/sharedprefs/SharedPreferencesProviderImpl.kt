package com.zeroninedev.common.data.sharedprefs

import android.content.Context
import android.content.SharedPreferences

/**
 * Реализация [SharedPreferencesProvider]
 *
 * @param context контекст [Context] приложения
 **/
class SharedPreferencesProviderImpl(context: Context) : SharedPreferencesProvider {

    private var prefs: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override suspend fun clear() {
        prefs
            .edit()
            .clear()
            .apply()
    }

    override fun remove(key: String) {
        prefs
            .edit()
            .remove(key)
            .apply()
    }

    override fun putString(key: String, s: String?) {
        prefs
            .edit()
            .putString(key, s)
            .apply()
    }

    override fun putInt(key: String, int: Int) {
        prefs
            .edit()
            .putInt(key, int)
            .apply()
    }

    override fun putLong(key: String, long: Long) {
        prefs
            .edit()
            .putLong(key, long)
            .apply()
    }

    override fun putBoolean(key: String, boolean: Boolean) {
        prefs
            .edit()
            .putBoolean(key, boolean)
            .apply()
    }

    override fun putFloat(key: String, float: Float) {
        prefs
            .edit()
            .putFloat(key, float)
            .apply()
    }

    override fun putStringSet(key: String, set: Set<String>) {
        prefs
            .edit()
            .putStringSet(key, set)
            .apply()
    }

    override fun getString(key: String, default: String?): String? =
        prefs.getString(key, default)

    override fun getInt(key: String, default: Int): Int =
        prefs.getInt(key, default)

    override fun getLong(key: String, default: Long): Long =
        prefs.getLong(key, default)

    override fun getBoolean(key: String, default: Boolean): Boolean =
        prefs.getBoolean(key, default)

    override fun getFloat(key: String, default: Float): Float =
        prefs.getFloat(key, default)

    override fun getStringSet(key: String, default: Set<String>?): MutableSet<String>? =
        prefs.getStringSet(key, default)

    override fun contains(key: String): Boolean = prefs.contains(key)

    companion object {

        private const val PREFERENCES_NAME = "Yahhoo"
    }
}