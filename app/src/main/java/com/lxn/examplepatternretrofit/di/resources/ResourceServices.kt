package com.lxn.examplepatternretrofit.di.resources

import android.graphics.Color

interface ResourceServices {
    fun getString(key: String): String
    fun getString(key: String, vararg args: Any): String
    fun getString(key: Int, vararg args: Any): String
    fun getColor(key: Int) : Color?
}