package com.lxn.examplepatternretrofit.platform.recycleview
import android.util.SparseArray
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.util.isEmpty
import androidx.recyclerview.widget.RecyclerView


abstract class BaseRecyclerAdapter<ActionHolder> :
        RecyclerView.Adapter<BaseViewHolder<Any, ActionHolder>>() {
    var listener: ItemHolderListener<ActionHolder, Any>? = null
    private var mHolderTypeArray: SparseArray<Class<*>> =
            SparseArray()
    var itemList: MutableList<DataHolder<*>> = ArrayList()
    protected abstract fun addHoldersMap(holderTypeArray: SparseArray<Class<*>>)
    override fun getItemCount(): Int {
        return itemList.size
    }

    init {
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<Any, ActionHolder>) {
        super.onViewAttachedToWindow(holder)
        addListenerViewHolder(holder)
        holder.onAttachToView()
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<Any, ActionHolder>) {
        super.onViewDetachedFromWindow(holder)
        holder.listener = null
        holder.onDetachFromView()
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].holderType.viewTypeValue
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): BaseViewHolder<Any, ActionHolder> {
        if (mHolderTypeArray.isEmpty()) {
            addHoldersMap(mHolderTypeArray)
        }
        return createHolder(parent, viewType)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<Any, ActionHolder>,
        position: Int
    ) {
        addListenerViewHolder(holder)
        itemList[position].data?.let {
            holder.data = itemList[position].data
            holder.onBind(it)
        }

    }

    private fun addListenerViewHolder(holder: BaseViewHolder<Any, ActionHolder>) {
        listener?.let {
            if (holder.listener == null) {
                holder.listener = listener
            }
        }
    }

    fun deleteItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItems(listData: List<Any>, iHolderType: HolderType) {
        for (item in listData) {
            itemList.add(DataHolderImpl(iHolderType, item))
        }
        notifyItemRangeInserted(itemCount - listData.size, itemCount)
    }

    fun addItem(data: Any?, iHolderType: HolderType) {
        itemList.add(DataHolderImpl(iHolderType, data))
        notifyItemInserted(itemList.size - 1)
    }

    fun addItem(data: Any?, iHolderType: HolderType, index: Int) {
        if (index < itemCount) {
            itemList.add(index, DataHolderImpl(iHolderType, data))
            notifyItemInserted(index)
        } else {
            itemList.add(DataHolderImpl(iHolderType, data))
            notifyItemInserted(itemCount - 1)
        }
    }

    fun setItem(data: Any?, iHolderType: HolderType, index: Int) {
        if (itemCount > index) {
            itemList[index] = DataHolderImpl(iHolderType, data)
        } else {
            itemList.add(DataHolderImpl(iHolderType, data))
        }
        notifyItemChanged(index)
    }

    fun getItem(posistion: Int): DataHolder<*>? {
        return if (itemList.size > posistion) {
            itemList[posistion]
        } else null
    }

    private fun createHolder(
            parent: ViewGroup,
            viewType: Int
    ): BaseViewHolder<Any, ActionHolder> {
        val clazz = mHolderTypeArray.get(viewType)
        try {
            val constructor = clazz.getConstructor(ViewGroup::class.java)
            return constructor.newInstance(parent) as BaseViewHolder<Any, ActionHolder>
        } catch (e: Exception) {
//            LogUtil.privatentStackTrace(e)
        }
        return object : BaseViewHolder<Any, ActionHolder>(parent) {
            override fun onBind(data: Any) {
            }
        }
    }

    fun remove(data: DataHolder<*>) {
        val index = itemList.indexOf(data)
        itemList.remove(data)
        notifyItemRemoved(index)
    }
}
