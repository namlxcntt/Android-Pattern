package com.lxn.examplepatternretrofit.di.module

import com.lxn.examplepatternretrofit.data.datasource.cache.db.MatchDao
import com.lxn.examplepatternretrofit.data.datasource.cache.db.movie.MovieDao
import com.lxn.examplepatternretrofit.data.datasource.cache.mappers.CacheMapper
import com.lxn.examplepatternretrofit.data.datasource.cache.mappers.MovieCacheMapper
import com.lxn.examplepatternretrofit.data.datasource.network.mappers.MovieMapper
import com.lxn.examplepatternretrofit.data.datasource.network.mappers.NetworkMapper
import com.lxn.examplepatternretrofit.data.datasource.network.retrofit.MatchRetrofit
import com.lxn.examplepatternretrofit.data.datasource.network.retrofit.TheMovieDbRetrofit
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
        movieDao: MovieDao,
        movieDbRetrofit: TheMovieDbRetrofit,
        cacheMapper: MovieCacheMapper,
        movieMapper: MovieMapper
    ): MainRepository {
        return MainRepository(
            movieDao = movieDao,
            movieRetrofit = movieDbRetrofit,
            cacheMapper = cacheMapper,

        )
    }
}