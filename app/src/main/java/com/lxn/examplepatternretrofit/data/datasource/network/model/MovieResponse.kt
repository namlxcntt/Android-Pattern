package com.lxn.examplepatternretrofit.data.datasource.network.model


import com.google.gson.annotations.SerializedName
import com.lxn.examplepatternretrofit.data.datasource.network.model.popular.Movie
import java.io.Serializable

data class MovieResponse(
    val page: Int,
    @SerializedName("results")
    val movieList: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
): Serializable