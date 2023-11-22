package com.movies.db.movies.infrastructure.repositories

import com.movies.db.shared.domain.core.Resource
import com.movies.db.movies.domain.datasources.MoviesRemoteDataSource
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.domain.repository.MoviesDbRepository
import com.movies.db.movies.infrastructure.datasources.MoviesDataSourceFactory
import com.movies.db.shared.infrastructure.config.util.Constants
import javax.inject.Inject


class MoviesDbRepositoryImpl @Inject constructor(private val moviesDataSourceFactory: MoviesDataSourceFactory) :
    MoviesDbRepository {
    override suspend fun getNowPlaying(page: Int, refresh: Boolean): Resource<List<Movie>> {
        val movies = moviesDataSourceFactory.getNowPlaying(page, refresh)
        return if (movies.isNotEmpty()) {
            Resource.Success(movies)
        } else {
            Resource.Error(Constants.ERROR_GET_DATA)
        }
    }
}