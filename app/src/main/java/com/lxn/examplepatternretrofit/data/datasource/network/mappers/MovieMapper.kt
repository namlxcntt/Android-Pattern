package com.lxn.examplepatternretrofit.data.datasource.network.mappers

import com.lxn.examplepatternretrofit.data.datasource.network.model.MovieResponse
import com.lxn.examplepatternretrofit.data.model.EntityMapper
import com.lxn.examplepatternretrofit.data.model.Movie
import javax.inject.Inject

/**
 * @author Namlxcntt
 * Create on 30/07/2021
 * Contact me: namlxcntt@gmail.com
 */
class MovieMapper @Inject constructor() : EntityMapper<MovieResponse, Movie> {
    override fun mapFromEntity(entity: MovieResponse): Movie {
        return Movie(
            page = entity.page,
            movieList = entity.movieList,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults
        )
    }

    override fun mapToEntity(domainModel: Movie): MovieResponse {
        return MovieResponse(
            page = domainModel.page,
            movieList = domainModel.movieList,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults
        )
    }
}