package com.lxn.examplepatternretrofit.data.datasource.network.retrofit

import com.lxn.examplepatternretrofit.data.datasource.network.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Namlxcntt
 * Create on 30/07/2021
 * Contact me: namlxcntt@gmail.com
 */
interface TheMovieDbRetrofit {

    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("page") page: Int): MovieResponse
}