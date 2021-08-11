package com.lxn.examplepatternretrofit.data.datasource.cache.db.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lxn.examplepatternretrofit.data.datasource.cache.model.MatchCacheEntity
import com.lxn.examplepatternretrofit.data.datasource.cache.model.MovieCacheEntity

/**
 * @author Namlxcntt
 * Create on 30/07/2021
 * Contact me: namlxcntt@gmail.com
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(match: MovieCacheEntity): Long

    @Query("SELECT * FROM MOVIE_TABLE_NAME")
    suspend fun get(): MovieCacheEntity
}