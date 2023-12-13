package com.movies.db.movies.infrastructure.repositories

import com.movies.db.app.core.Resource
import com.movies.db.movies.domain.datasources.IMoviesDataSource
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.domain.repository.IMoviesDbRepository
import javax.inject.Inject


class MoviesDbRepository @Inject constructor(
    private val moviesDataSource: IMoviesDataSource,
) : IMoviesDbRepository {
    override suspend fun getNowPlaying(page: Int, refresh: Boolean): Resource<List<Movie>> {
        return moviesDataSource.getNowPlaying(page, refresh)
    }

    override suspend fun getPopular(page: Int, refresh: Boolean): Resource<List<Movie>> {
        return moviesDataSource.getPopular(page, refresh)
    }

    override suspend fun getTopRated(page: Int, refresh: Boolean): Resource<List<Movie>> {
        return moviesDataSource.getTopRated(page, refresh)
    }

    override suspend fun getUpcoming(page: Int, refresh: Boolean): Resource<List<Movie>> {
        return moviesDataSource.getUpcoming(page, refresh)
    }

}