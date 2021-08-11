package com.lxn.examplepatternretrofit.data.model

import com.lxn.examplepatternretrofit.data.datasource.network.model.popular.Movie

/**
 * @author Namlxcntt
 * Create on 30/07/2021
 * Contact me: namlxcntt@gmail.com
 */
class Movie(
    val page: Int,
    val movieList: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)