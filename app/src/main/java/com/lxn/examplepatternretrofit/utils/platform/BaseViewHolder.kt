package com.lxn.examplepatternretrofit.utils.platform

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * @author Namlxcntt
 * Create on 29/07/2021
 * Contact me: namlxcntt@gmail.com
 */
abstract class BaseViewHolder<Data>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    var data: Data? = null


    abstract fun onBind(data: Data)


}