package com.movies.db.infrastructure.datasources

import com.movies.db.domain.core.Resource
import com.movies.db.domain.datasources.MoviesDataSource
import com.movies.db.domain.entities.Movie
import com.movies.db.infrastructure.config.util.Constants.THE_MOVIEDB_KEY
import com.movies.db.infrastructure.data.remote.MoviesDbApi
import com.movies.db.infrastructure.mappers.MovieMapper
import javax.inject.Inject

class MoviesDataSourceImpl @Inject constructor(
    private val api: MoviesDbApi,
    private val movieMapper: MovieMapper,
) : MoviesDataSource {
    override suspend fun getNowPlaying(page: Int): Resource<List<Movie>> {
        return when (val response =
            api.getNowPlaying(authorization = "Bearer $THE_MOVIEDB_KEY", page)) {
            is Resource.Success -> {
                Resource.Success(movieMapper.mapMoviesDBResponseItemListToMovieList(response.data!!))
            }

            else -> {
                Resource.Error(response.message)

            }
        }

    }
}