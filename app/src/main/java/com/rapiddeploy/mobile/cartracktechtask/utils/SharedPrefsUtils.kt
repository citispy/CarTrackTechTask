package com.rapiddeploy.mobile.cartracktechtask.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val PREFS = "prefs"
const val KEY_TITLE = "title"

class SharedPrefsUtils @Inject constructor(@ApplicationContext context: Context) {
    private var sharedPrefs: SharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    fun savePrefs(key: String, value: String) {
        sharedPrefs.edit().putString(key, value).apply()
    }

    fun getPrefs(key: String): String? {
        return sharedPrefs.getString(key, null)
    }
}