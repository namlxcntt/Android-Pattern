package com.lxn.examplepatternretrofit.repository

import com.lxn.examplepatternretrofit.constant.DataState
import com.lxn.examplepatternretrofit.data.datasource.cache.db.MatchDao
import com.lxn.examplepatternretrofit.data.datasource.cache.db.movie.MovieDao
import com.lxn.examplepatternretrofit.data.datasource.cache.mappers.CacheMapper
import com.lxn.examplepatternretrofit.data.datasource.cache.mappers.MovieCacheMapper
import com.lxn.examplepatternretrofit.data.datasource.cache.model.MovieCacheEntity
import com.lxn.examplepatternretrofit.data.datasource.network.mappers.MovieMapper
import com.lxn.examplepatternretrofit.data.datasource.network.mappers.NetworkMapper
import com.lxn.examplepatternretrofit.data.datasource.network.model.MovieResponse
import com.lxn.examplepatternretrofit.data.datasource.network.retrofit.MatchRetrofit
import com.lxn.examplepatternretrofit.data.datasource.network.retrofit.TheMovieDbRetrofit
import com.lxn.examplepatternretrofit.data.model.Match
import com.lxn.examplepatternretrofit.data.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Singleton


class MainRepository constructor(
    private val movieDao: MovieDao,
    private val movieRetrofit: TheMovieDbRetrofit,
    private val cacheMapper: MovieCacheMapper,
    private val networkMapper: MovieMapper
) {

    suspend fun getMoviePopular(): Flow<DataState<Movie>> = flow {
        emit(DataState.Loading)
        delay(1000L)
        try {
            val netWorkMovie = movieRetrofit.getPopularMovie(0)
            val movie = networkMapper.mapFromEntity(netWorkMovie)
            movieDao.insert(match = cacheMapper.mapToEntity(movie))
            val cacheMovie = movieDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntity(cacheMovie)))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }
//    suspend fun getMatch(): Flow<DataState<List<Match>>> = flow {
//        emit(DataState.Loading)
//        delay(2000)
//        try {
//            val networkMatch = matchRetrofit.get()
//            val matchs = networkMapper.mapFromEntityList(networkMatch)
//            for (match in matchs) {
//                matchDao.insert(cacheMapper.mapToEntity(match))
//            }
//            val cacheMatch = matchDao.get()
//            emit(DataState.Success(cacheMapper.mapFromEntityList(cacheMatch)))
//        } catch (exception: Exception) {
//            emit(DataState.Error(exception))
//        }
//    }
}