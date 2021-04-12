package com.lxn.examplepatternretrofit.presentation.adapter

import android.util.SparseArray
import com.lxn.examplepatternretrofit.platform.recycleview.BaseRecyclerAdapter
import com.lxn.examplepatternretrofit.platform.recycleview.HolderType


class MatchAdapter : BaseRecyclerAdapter<MatchAdapter.ActionHolder>() {

    override fun addHoldersMap(holderTypeArray: SparseArray<Class<*>>) {
        holderTypeArray.put(TypeHolder.MATCH_ITEM.viewTypeValue, MatchViewHolder::class.java)
    }

    enum class TypeHolder : HolderType {
        MATCH_ITEM {
            override val viewTypeValue: Int
                get() = 1
        }
    }

    enum class ActionHolder {
        CLICK_ITEM_MATCH
    }

}
