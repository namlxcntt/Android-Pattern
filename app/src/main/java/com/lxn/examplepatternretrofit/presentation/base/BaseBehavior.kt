package com.lxn.examplepatternretrofit.presentation.base

interface BaseBehavior {
    fun onKeyboardShowing(isShowing: Boolean) {}
    fun onLoading(isLoading: Boolean) {}
    fun onError() {}
    fun onNetworkStatusChanged(isConnected: Boolean) {}
    fun onViewLoaded(){}
    fun onSetupView()

}