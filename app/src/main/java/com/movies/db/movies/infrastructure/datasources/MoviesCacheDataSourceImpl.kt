package com.movies.db.movies.infrastructure.datasources

import com.movies.db.movies.domain.datasources.MoviesCacheDataSource
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.infrastructure.data.remote.MoviesDbApi
import com.movies.db.movies.infrastructure.mappers.MovieMapper
import com.movies.db.shared.domain.core.Resource
import javax.inject.Inject

class MoviesCacheDataSourceImpl @Inject constructor(
    private val api: MoviesDbApi,
    private val movieMapper: MovieMapper,
) : MoviesCacheDataSource {
    override suspend fun getNowPlaying(): Resource<List<Movie>> {
       return Resource.Success(emptyList<Movie>())
    }
}