package com.lxn.examplepatternretrofit.data.datasource.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lxn.examplepatternretrofit.data.datasource.cache.model.MatchCacheEntity

@Database(entities = [MatchCacheEntity::class], version = 1, exportSchema = false)
abstract class MatchDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao

    companion object {
        const val DATABASE_NAME: String = "match_db"
    }
}