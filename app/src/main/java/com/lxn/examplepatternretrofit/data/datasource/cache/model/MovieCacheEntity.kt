package com.lxn.examplepatternretrofit.data.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lxn.examplepatternretrofit.constant.Constant.MOVIE_TABLE_NAME
import com.lxn.examplepatternretrofit.data.datasource.cache.Converters
import com.lxn.examplepatternretrofit.data.datasource.network.model.popular.Movie

/**
 * @author Namlxcntt
 * Create on 30/07/2021
 * Contact me: namlxcntt@gmail.com
 */
@Entity(tableName = MOVIE_TABLE_NAME)
class MovieCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "page")
    val page: Int,

    @ColumnInfo(name = "movieList")
    @TypeConverters(Converters::class)
    val movieList: List<Movie>,

    @ColumnInfo(name = "totalPages")
    val totalPages: Int,

    @ColumnInfo(name = "totalResults")
    val totalResults: Int
)
