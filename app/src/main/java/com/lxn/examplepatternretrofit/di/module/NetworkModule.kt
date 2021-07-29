package com.lxn.examplepatternretrofit.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lxn.examplepatternretrofit.constant.Constant
import com.lxn.examplepatternretrofit.data.model.EntityMapper
import com.lxn.examplepatternretrofit.data.model.Match
import com.lxn.examplepatternretrofit.data.datasource.network.mappers.NetworkMapper
import com.lxn.examplepatternretrofit.data.datasource.network.model.MatchObjectNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpCache(@ApplicationContext context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    @Singleton
    fun provideNetworkMapper(): EntityMapper<MatchObjectNetwork, Match> {
        return NetworkMapper()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.cache(cache)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        logging.level = HttpLoggingInterceptor.Level.BODY

        client.connectTimeout(Constant.LOADER_EXPIRED_TIME.toLong(), TimeUnit.SECONDS)
        client.readTimeout(Constant.LOADER_EXPIRED_TIME.toLong(), TimeUnit.SECONDS)
        client.writeTimeout(Constant.LOADER_EXPIRED_TIME.toLong(), TimeUnit.SECONDS)
        client.addInterceptor { chain ->
            val original = chain.request()
            val request: Request
            val builder: Request.Builder = original.newBuilder()
                .method(original.method(), original.body())
            request = builder.build()

            return@addInterceptor chain.proceed(request)
        }
        client.addInterceptor(logging)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
    }

}