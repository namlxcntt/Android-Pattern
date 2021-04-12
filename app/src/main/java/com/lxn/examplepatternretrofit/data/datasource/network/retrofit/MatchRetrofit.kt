package com.lxn.examplepatternretrofit.data.datasource.network.retrofit

import com.lxn.examplepatternretrofit.data.datasource.network.model.MatchObjectNetwork
import retrofit2.http.GET

interface MatchRetrofit {

    @GET("lichthidau/all")
    suspend fun get(): List<MatchObjectNetwork>
}