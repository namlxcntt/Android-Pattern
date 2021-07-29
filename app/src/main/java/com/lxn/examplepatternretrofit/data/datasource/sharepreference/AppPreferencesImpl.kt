package com.lxn.examplepatternretrofit.data.datasource.sharepreference

import android.content.Context
import android.content.SharedPreferences
import com.lxn.examplepatternretrofit.BuildConfig
import javax.inject.Inject

class AppPreferencesImpl @Inject constructor(context: Context) : AppPreferences {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

    override fun getSharePreferences(): SharedPreferences {
        return sharedPreferences
    }

    override fun saveStringToPreference() {
        getSharePreferences().edit().putString("Namlx", "Data").apply()
    }

}