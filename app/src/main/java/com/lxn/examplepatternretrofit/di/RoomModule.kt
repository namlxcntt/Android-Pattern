package com.lxn.examplepatternretrofit.di

import android.content.Context
import androidx.room.Room
import com.lxn.examplepatternretrofit.data.datasource.cache.db.MatchDao
import com.lxn.examplepatternretrofit.data.datasource.cache.db.MatchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Provides
    @Singleton
    fun provideMatchDb(@ApplicationContext context: Context): MatchDatabase {
        return Room.databaseBuilder(context, MatchDatabase::class.java, MatchDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMatchDao(matchDatabase: MatchDatabase): MatchDao {
        return matchDatabase.matchDao()
    }

}