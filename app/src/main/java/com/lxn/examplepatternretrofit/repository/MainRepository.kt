package com.lxn.examplepatternretrofit.repository

import com.lxn.examplepatternretrofit.constant.DataState
import com.lxn.examplepatternretrofit.data.datasource.cache.db.MatchDao
import com.lxn.examplepatternretrofit.data.datasource.cache.mappers.CacheMapper
import com.lxn.examplepatternretrofit.data.datasource.network.mappers.NetworkMapper
import com.lxn.examplepatternretrofit.data.datasource.network.retrofit.MatchRetrofit
import com.lxn.examplepatternretrofit.data.model.Match
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Singleton


class MainRepository constructor(
    private val matchDao: MatchDao,
    private val matchRetrofit: MatchRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getMatch(): Flow<DataState<List<Match>>> = flow {
        emit(DataState.Loading)
        delay(2000)
        try {
            val networkMatch = matchRetrofit.get()
            val matchs = networkMapper.mapFromEntityList(networkMatch)
            for (match in matchs) {
                matchDao.insert(cacheMapper.mapToEntity(match))
            }
            val cacheMatch = matchDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cacheMatch)))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }
}