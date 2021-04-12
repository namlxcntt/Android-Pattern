package com.lxn.examplepatternretrofit.platform.recycleview


class DataHolderImpl<T>(override var holderType: HolderType, override var data: T) : DataHolder<T> {

}
