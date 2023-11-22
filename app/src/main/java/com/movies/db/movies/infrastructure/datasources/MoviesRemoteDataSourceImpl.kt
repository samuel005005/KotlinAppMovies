package com.movies.db.movies.infrastructure.datasources

import com.movies.db.shared.domain.core.Resource
import com.movies.db.movies.domain.datasources.MoviesRemoteDataSource
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.shared.infrastructure.config.util.Constants.THE_MOVIEDB_KEY
import com.movies.db.movies.infrastructure.data.remote.MoviesDbApi
import com.movies.db.movies.infrastructure.mappers.MovieToResponseMapper
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val api: MoviesDbApi,
    private val movieMapper: MovieToResponseMapper,
) : MoviesRemoteDataSource {
    override suspend fun getNowPlaying(page: Int): List<Movie>? {
        val response = api.getNowPlaying(authorization = "Bearer $THE_MOVIEDB_KEY", page)
        return if (response.isSuccessful && response.body() != null) {
            val movies = response.body()
            movies?.results?.map {
                movieMapper.map(it)
            }
        } else {
            null
        }
    }
}