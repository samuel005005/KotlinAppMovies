package com.movies.db.movies.infrastructure.repositories

import com.movies.db.shared.domain.core.Resource
import com.movies.db.movies.domain.datasources.MoviesRemoteDataSource
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.domain.repository.MoviesDbRepository
import com.movies.db.movies.infrastructure.datasources.MoviesDataSourceFactory
import javax.inject.Inject


class MoviesDbRepositoryImpl @Inject constructor(private val moviesDataSourceFactory: MoviesDataSourceFactory) :
    MoviesDbRepository {
    override suspend fun getNowPlaying(page: Int): Resource<List<Movie>> {
        return moviesDataSourceFactory.getNowPlaying()
    }
}