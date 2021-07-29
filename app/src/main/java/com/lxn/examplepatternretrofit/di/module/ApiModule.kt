package com.lxn.examplepatternretrofit.di.module

import com.lxn.examplepatternretrofit.data.datasource.network.retrofit.MatchRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Provides
    @Singleton
    fun provideMatchService(retrofit: Retrofit.Builder): MatchRetrofit {
        return retrofit.build().create(MatchRetrofit::class.java)
    }
}