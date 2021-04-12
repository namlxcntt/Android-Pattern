package com.lxn.examplepatternretrofit.platform.recycleview


interface ItemHolderListener<ActionHolder, Data> {
    fun onItemHolderClicked(actionHolder: ActionHolder, data: Data?=null, position: Int=-1)
}
