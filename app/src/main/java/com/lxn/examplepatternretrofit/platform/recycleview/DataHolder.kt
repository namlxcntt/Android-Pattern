package com.lxn.examplepatternretrofit.platform.recycleview

interface DataHolder<T> {
    var holderType: HolderType
    var data: T

}