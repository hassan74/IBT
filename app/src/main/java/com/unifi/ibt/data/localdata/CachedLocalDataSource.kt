package com.unifi.ibt.data.localdata

import android.content.SharedPreferences
import com.unifi.ibt.data.IDataSource
import com.unifi.ibt.data.Result
import java.lang.Exception

class CachedLocalDataSource(var sharedPref: SharedPreferences): IDataSource {
    override
    fun fetchData(): Result<String> {
        sharedPref.getString("local_words", null)?.apply {
            return Result.Success(this)
        }
        return Result.Error(Exception("No Cached Data"))
    }

    override
    fun saveData(response: String) {
        with(sharedPref.edit()) {
            putString("local_words", response)
            apply()
        }
    }

}