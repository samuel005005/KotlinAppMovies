package com.movies.db.infrastructure.repositories

import com.movies.db.domain.core.Resource
import com.movies.db.domain.datasources.MoviesDataSource
import com.movies.db.domain.entities.Movie
import com.movies.db.domain.repository.MoviesDbRepository
import javax.inject.Inject


class MoviesDbRepositoryImpl @Inject constructor(private val moviesDataSource: MoviesDataSource) :
    MoviesDbRepository {
    override suspend fun getNowPlaying(page: Int): Resource<List<Movie>> {
        return moviesDataSource.getNowPlaying(page)
    }
}