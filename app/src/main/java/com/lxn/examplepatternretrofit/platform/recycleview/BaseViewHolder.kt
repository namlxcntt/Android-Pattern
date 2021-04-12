package com.lxn.examplepatternretrofit.platform.recycleview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder<Data, ActionHolder> : RecyclerView.ViewHolder {
    var listener: ItemHolderListener<ActionHolder, Any>? = null
    var data: Data? = null

    // constructor to default
    @SuppressLint("ResourceType")
    constructor(parent: ViewGroup) : super(
            LayoutInflater.from(parent.context).inflate(
                    android.R.id.content,
                    parent,
                    false
            )
    )

    // constructor to infused layoutID
    constructor(parent: ViewGroup, @LayoutRes layoutId: Int) : super(
            LayoutInflater.from(parent.context).inflate(
                    layoutId,
                    parent,
                    false
            )
    )

    abstract fun onBind(data: Data)

    open fun onAttachToView() {
    }

    open fun onDetachFromView() {
    }
}