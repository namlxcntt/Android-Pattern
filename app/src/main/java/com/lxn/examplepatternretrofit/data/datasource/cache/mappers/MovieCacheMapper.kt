package com.lxn.examplepatternretrofit.data.datasource.cache.mappers

import com.lxn.examplepatternretrofit.data.datasource.cache.model.MovieCacheEntity
import com.lxn.examplepatternretrofit.data.model.EntityMapper
import com.lxn.examplepatternretrofit.data.model.Movie
import javax.inject.Inject

/**
 * @author Namlxcntt
 * Create on 30/07/2021
 * Contact me: namlxcntt@gmail.com
 */
class MovieCacheMapper @Inject constructor() : EntityMapper<MovieCacheEntity, Movie> {
    override fun mapFromEntity(entity: MovieCacheEntity): Movie {
        return Movie(
            page = entity.page,
            movieList = entity.movieList,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults
        )
    }

    override fun mapToEntity(domainModel: Movie): MovieCacheEntity {
        return MovieCacheEntity(
            page = domainModel.page,
            movieList = domainModel.movieList,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults
        )
    }
}