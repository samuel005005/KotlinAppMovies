package com.movies.db.movies.infrastructure.datasources

import com.movies.db.shared.domain.core.Resource
import com.movies.db.movies.domain.datasources.MoviesRemoteDataSource
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.shared.infrastructure.config.util.Constants.THE_MOVIEDB_KEY
import com.movies.db.movies.infrastructure.data.remote.MoviesDbApi
import com.movies.db.movies.infrastructure.mappers.MovieMapper
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val api: MoviesDbApi,
    private val movieMapper: MovieMapper,
) : MoviesRemoteDataSource {
    override suspend fun getNowPlaying(page: Int): Resource<List<Movie>> {
        return try {
            val response = api.getNowPlaying(authorization = "Bearer $THE_MOVIEDB_KEY", page)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(movieMapper.mapMoviesDBResponseItemListToMovieList(response.body()!!))
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(
                e.message ?: ""
            )
        }
    }
}