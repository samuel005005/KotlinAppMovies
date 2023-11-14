package com.moviesapp.infrastructure.repositories

import com.moviesapp.domain.core.Resource
import com.moviesapp.domain.datasources.MoviesDataSource
import com.moviesapp.domain.entities.Movie
import com.moviesapp.domain.repository.MoviesDbRepository
import javax.inject.Inject

class MoviesDbRepository @Inject constructor(private val moviesDataSource: MoviesDataSource) :
    MoviesDbRepository {
    override suspend fun getNowPlaying(page: Int): Resource<List<Movie>> {
        return moviesDataSource.getNowPlaying(page)
    }
}