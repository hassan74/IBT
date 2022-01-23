package com.unifi.ibt.data

import android.content.SharedPreferences
import java.lang.Exception

class CachedLocalDataSource(var sharedPref: SharedPreferences) {
    fun getCachedData(): Result<String> {
        sharedPref.getString("local_words", null)?.apply {
            return Result.Success(this)
        }
        return Result.Error(Exception("No Cached Data"))
    }

    fun saveData(response: String) {
        with(sharedPref.edit()) {
            putString("local_words", response)
            apply()
        }
    }

}