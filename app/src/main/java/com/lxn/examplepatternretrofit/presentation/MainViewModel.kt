package com.lxn.examplepatternretrofit.presentation

import android.util.Log
import androidx.documentfile.R
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.lxn.examplepatternretrofit.constant.DataState
import com.lxn.examplepatternretrofit.data.datasource.sharepreference.AppPreferences
import com.lxn.examplepatternretrofit.data.model.Match
import com.lxn.examplepatternretrofit.di.resources.ResourceServices
import com.lxn.examplepatternretrofit.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val appPreferences: AppPreferences,
    private val resourceServices: ResourceServices
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<Match>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Match>>>
        get() = _dataState

    fun getMatch() {
        viewModelScope.launch {
            mainRepository.getMatch().onEach { dataState ->
                _dataState.value = dataState
            }.launchIn(viewModelScope)
        }
        appPreferences.saveStringToPreference()
    }

}
