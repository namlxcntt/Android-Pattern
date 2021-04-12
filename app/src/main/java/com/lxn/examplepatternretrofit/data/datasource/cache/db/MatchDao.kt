package com.lxn.examplepatternretrofit.data.datasource.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lxn.examplepatternretrofit.data.model.Match
import com.lxn.examplepatternretrofit.data.datasource.cache.model.MatchCacheEntity


@Dao
interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(match: MatchCacheEntity): Long

    @Query("SELECT * FROM `match`")
    suspend fun get(): List<MatchCacheEntity>
}