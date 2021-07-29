package com.lxn.examplepatternretrofit.utils.platform

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.lxn.examplepatternretrofit.data.model.Match


/**
 * @author Namlxcntt
 * Create on 29/07/2021
 * Contact me: namlxcntt@gmail.com
 */
abstract class BaseRecycleViewAdapter<T : ViewBinding> : RecyclerView.Adapter<BaseViewHolder<Any>>() {
    var itemList: ArrayList<Any> = ArrayList()
    lateinit var binding: T

    override fun getItemCount() = itemList.size

    fun addAllList(list: ArrayList<Any>) {
        this.itemList.apply {
            clear()
            addAll(list)
            notifyDataSetChanged()
        }
    }
    abstract fun inflateLayout(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        isAttack: Boolean
    ): T

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        binding = inflateLayout(LayoutInflater.from(parent.context), parent, false)
        return object : BaseViewHolder<Any>(binding) {
            override fun onBind(data: Any) {
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {
        holder.onBind(itemList[position])
    }
}