package com.lxn.examplepatternretrofit.data.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match")
class MatchCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "doi1")
    var doi1: String,

    @ColumnInfo(name = "doi2")
    var doi2: String,

    @ColumnInfo(name = "gio")
    var gio: String,

    @ColumnInfo(name = "kenh")
    var kenh: String,

    @ColumnInfo(name = "ngay")
    var ngay: String,

    @ColumnInfo(name = "quocky1")
    var quocky1: String,

    @ColumnInfo(name = "quocky2")
    var quocky2: String,

    @ColumnInfo(name = "vong")
    var vong: String
)