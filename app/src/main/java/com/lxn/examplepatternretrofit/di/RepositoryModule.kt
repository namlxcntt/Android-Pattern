package com.lxn.examplepatternretrofit.di

import com.lxn.examplepatternretrofit.data.datasource.cache.db.MatchDao
import com.lxn.examplepatternretrofit.data.datasource.cache.mappers.CacheMapper
import com.lxn.examplepatternretrofit.data.datasource.network.mappers.NetworkMapper
import com.lxn.examplepatternretrofit.data.datasource.network.retrofit.MatchRetrofit
import com.lxn.examplepatternretrofit.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        matchDao: MatchDao,
        matchRetrofit: MatchRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(
            matchDao = matchDao,
            matchRetrofit = matchRetrofit,
            cacheMapper = cacheMapper,
            networkMapper = networkMapper,
        )
    }
}