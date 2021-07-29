package com.lxn.examplepatternretrofit.presentation.base

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.lxn.examplepatternretrofit.data.datasource.sharepreference.AppPreferences
import com.lxn.examplepatternretrofit.di.resources.ResourceServices
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
abstract class BaseViewModel : ViewModel() {
    lateinit var resourcesService: ResourceServices
    lateinit var appPreferences: AppPreferences

    internal fun init(
        appPreferences: AppPreferences,
        resourcesService: ResourceServices
    ) {
        this.appPreferences = appPreferences
        this.resourcesService = resourcesService
    }

    override fun onCleared() {
        super.onCleared()

    }
}