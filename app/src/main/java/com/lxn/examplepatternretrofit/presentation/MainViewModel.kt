package com.lxn.examplepatternretrofit.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.lxn.examplepatternretrofit.constant.DataState
import com.lxn.examplepatternretrofit.data.model.Match
import com.lxn.examplepatternretrofit.presentation.base.BaseViewModel
import com.lxn.examplepatternretrofit.repository.MainRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {


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

//    fun safeBreakingGetMatch() {
//        if (checkInternetConnection()) {
//            getMatch()
//        } else {
//            Log.e("TAG", "No internet connection")
//        }
//    }
//
//    private fun observerInternetConnection(): Boolean {
//
//    }

//    private fun checkInternetConnection(): Boolean {
//        val connectivityManager = getApplication<MyApplication>().getSystemService(
//            Context.CONNECTIVITY_SERVICE
//        ) as ConnectivityManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val activeNetwork = connectivityManager.activeNetwork ?: return false
//            val capabilities =
//                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
//            return when {
//                capabilities.hasTransport(TRANSPORT_WIFI) -> true
//                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
//                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
//                capabilities.hasTransport(TRANSPORT_WIFI) -> true
//                else -> false
//            }
//        } else {
//            connectivityManager.activeNetworkInfo?.run {
//                return when (type) {
//                    TYPE_WIFI -> true
//                    TYPE_MOBILE -> true
//                    TYPE_ETHERNET -> true
//                    else -> false
//                }
//            }
//        }
//        return false
//    }
}
