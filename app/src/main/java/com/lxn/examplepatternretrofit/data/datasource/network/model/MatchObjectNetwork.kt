package com.lxn.examplepatternretrofit.data.datasource.network.model


import com.google.gson.annotations.SerializedName

data class MatchObjectNetwork(
    @SerializedName("doi1")
    var doi1: String,

    @SerializedName("doi2")
    var doi2: String,

    @SerializedName("gio")
    var gio: String,

    @SerializedName("id")
    var id: Int,

    @SerializedName("kenh")
    var kenh: String,

    @SerializedName("ngay")
    var ngay: String,

    @SerializedName("quocky1")
    var quocky1: String,

    @SerializedName("quocky2")
    var quocky2: String,

    @SerializedName("vong")
    var vong: String
)