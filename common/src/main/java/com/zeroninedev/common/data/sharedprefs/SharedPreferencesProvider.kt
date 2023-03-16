package com.zeroninedev.common.data.sharedprefs

interface SharedPreferencesProvider {

    suspend fun clear()

    fun remove(key: String)

    fun putString(key: String, s: String?)

    fun putInt(key: String, int: Int)

    fun putLong(key: String, long: Long)

    fun putBoolean(key: String, boolean: Boolean)

    fun putFloat(key: String, float: Float)

    fun putStringSet(key: String, set: Set<String>)

    fun getString(key: String, default: String? = ""): String?

    fun getInt(key: String, default: Int = 0): Int

    fun getLong(key: String, default: Long = 0L): Long

    fun getBoolean(key: String, default: Boolean = false): Boolean

    fun getFloat(key: String, default: Float = 0F): Float

    fun getStringSet(key: String, default: Set<String>?): Set<String>?

    fun contains(key: String): Boolean
}