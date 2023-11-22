package com.movies.db.movies.domain.datasources

import com.movies.db.shared.domain.core.Resource
import com.movies.db.movies.domain.entities.Movie

interface MoviesRemoteDataSource {
    suspend fun getNowPlaying(page: Int): List<Movie>?
}