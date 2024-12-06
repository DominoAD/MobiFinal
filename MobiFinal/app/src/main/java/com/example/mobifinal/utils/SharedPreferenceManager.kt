// utils/SharedPreferenceManager.kt
package com.example.mobifinal.utils

import android.content.SharedPreferences

class SharedPreferenceManager(private val preferences: SharedPreferences) : SharedPreferences {
    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_USER_TOKEN = "user_token"
    }

    fun setUserLoggedIn(email: String, token: String) {
        preferences.edit()
            .putBoolean(KEY_IS_LOGGED_IN, true)
            .putString(KEY_USER_EMAIL, email)
            .putString(KEY_USER_TOKEN, token)
            .apply()
    }

    fun isUserLoggedIn(): Boolean {
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun getUserEmail(): String? {
        return preferences.getString(KEY_USER_EMAIL, null)
    }

    fun clearUserSession() {
        preferences.edit().clear().apply()
    }

    override fun getAll(): MutableMap<String, *> {
        TODO("Not yet implemented")
    }

    override fun getString(key: String?, defValue: String?): String? {
        TODO("Not yet implemented")
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String>? {
        TODO("Not yet implemented")
    }

    override fun getInt(key: String?, defValue: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getLong(key: String?, defValue: Long): Long {
        TODO("Not yet implemented")
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        TODO("Not yet implemented")
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(key: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun edit(): SharedPreferences.Editor {
        TODO("Not yet implemented")
    }

    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("Not yet implemented")
    }

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("Not yet implemented")
    }
}

