package com.movies.db.infrastructure.repositories

import com.movies.db.domain.core.Resource
import com.movies.db.domain.entities.Movie
import com.movies.db.domain.repository.MoviesDbRepository
import com.movies.db.infrastructure.datasources.MoviesDataSourceImpl
import javax.inject.Inject


class MoviesDbRepositoryImpl @Inject constructor(private val moviesDataSourceImpl: MoviesDataSourceImpl) :
    MoviesDbRepository {
    override suspend fun getNowPlaying(page: Int): Resource<List<Movie>> {
        return moviesDataSourceImpl.getNowPlaying(page)
    }
}