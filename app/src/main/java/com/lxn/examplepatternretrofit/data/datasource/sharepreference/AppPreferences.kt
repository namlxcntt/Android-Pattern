package com.lxn.examplepatternretrofit.data.datasource.sharepreference

import android.content.SharedPreferences

interface AppPreferences {
    fun getSharePreferences() : SharedPreferences

    fun saveStringToPreference()
}