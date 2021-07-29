package com.lxn.examplepatternretrofit.di.resources

import android.content.res.Resources
import android.graphics.Color

class ResourcesServicesImpl(private val resources: Resources, private val packageName: String) :
    ResourceServices {
    override fun getString(key: String): String {
        val identifier = resources.getIdentifier(key, "string", packageName)
        return if (identifier == 0) key else resources.getString(identifier)
    }

    override fun getString(key: String, vararg args: Any): String {
        val identifier = resources.getIdentifier(key, "string", packageName)
        return if (identifier == 0) key else resources.getString(identifier, *args)
    }

    override fun getString(key: Int, vararg args: Any): String {
        return resources.getString(key, *args)
    }

    override fun getColor(key: Int): Color? {
        return null
    }

}