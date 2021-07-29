package com.lxn.examplepatternretrofit.presentation.base

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Rect
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import com.lxn.examplepatternretrofit.R
import com.lxn.examplepatternretrofit.data.datasource.sharepreference.AppPreferences
import com.lxn.examplepatternretrofit.di.resources.ResourceServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.io.Serializable
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
abstract class BaseActivity : FragmentActivity(), BaseBehavior {

    private var alertDialogBuilder: AlertDialog.Builder? = null

    private var alertDialog: AlertDialog? = null

    @Inject
    lateinit var resourcesService: ResourceServices

    @Inject
    lateinit var appPreferences: AppPreferences

    @get:LayoutRes
    protected abstract val layoutId: Int?


    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        layoutId?.let { setContentView(it) }
        addNetworkCallBack()
        onSetupView()
        onViewLoaded()

    }

    open fun initViewModel(viewModel: BaseViewModel) {
        viewModel.init(appPreferences, resourcesService)
    }

    private fun addNetworkCallBack() {
        val request =
            NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED).build()
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(request,
            object : ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    onNetworkStatusChanged(false)
                }

                override fun onAvailable(network: Network) {
                    onNetworkStatusChanged(true)
                }
            })
        onNetworkStatusChanged(isNetworkConnected(this))
    }

    private fun isNetworkConnected(context: Context): Boolean {
        val activeNetworkInfo =
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        activeNetworkInfo?.let {
            return activeNetworkInfo.isConnected
        }
        return false
    }

    fun openActivity(cla: Class<*>, vararg flags: Int) {
        val intent = Intent(this, cla)
        for (flag in flags) {
            intent.addFlags(flag)
        }
        startActivity(intent)
    }

    fun openActivityWithParam(cla: Class<*>, vararg flags: Int, data: Any, key: String) {
        val intent = Intent(this, cla)
        for (flag in flags) {
            intent.addFlags(flag)
        }
        when (data) {
            is Int -> {
                intent.putExtra(key, data)
            }
            is Long -> {
                intent.putExtra(key, data)
            }
            is String -> {
                intent.putExtra(key, data)
            }
            is Serializable -> {
                intent.putExtra(key, data)
            }
            is Double -> {
                intent.putExtra(key, data)
            }
            is Boolean -> {
                intent.putExtra(key, data)
            }
            is Byte -> {
                intent.putExtra(key, data)
            }
            is ByteArray -> {
                intent.putExtra(key, data)
            }
            is Char -> {
                intent.putExtra(key, data)
            }
            is CharArray -> {
                intent.putExtra(key, data)
            }
        }
        startActivity(intent)
    }


    fun showAlert(
        title: String = getString(R.string.app_name),
        message: String,
        positiveButton: String = getString(R.string.cancel),
        positiveButtonListener: (() -> Unit)? = null,
        negativeButton: String = getString(R.string.ok),
        negativeButtonListener: (() -> Unit)? = null,
        dismissListener: DialogInterface.OnDismissListener? = null
    ) {
        if (alertDialog != null && alertDialog!!.isShowing) {
            return
        }
        alertDialog = AlertDialog.Builder(this).setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButton) { _, _ ->
                run {
                    positiveButtonListener?.invoke()
                    alertDialog?.dismiss()
                }
            }
            .setNegativeButton(negativeButton) { _, _ ->
                run {
                    negativeButtonListener?.invoke()
                    alertDialog?.dismiss()

                }
            }
            .setOnDismissListener(dismissListener)
            .setCancelable(false)
            .create()
        alertDialog?.show()
    }


    override fun onError() {
        super.onError()
    }

    override fun onKeyboardShowing(isShowing: Boolean) {
        super.onKeyboardShowing(isShowing)
    }

    fun showKeyboard(view: View) {
        view.post {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, 0)
        }
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onViewLoaded() {
        super.onViewLoaded()
    }

    override fun onLoading(isLoading: Boolean) {
    }

    override fun onNetworkStatusChanged(isConnected: Boolean) {
        super.onNetworkStatusChanged(isConnected)
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            if (currentFocus is EditText) {
                val outRect = Rect()
                (currentFocus as EditText).getGlobalVisibleRect(outRect)
                ev?.let {
                    if (outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                        return super.dispatchTouchEvent(ev)
                    }
                }
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                (currentFocus as EditText).clearFocus()
            }

        }
        return super.dispatchTouchEvent(ev)
    }

}