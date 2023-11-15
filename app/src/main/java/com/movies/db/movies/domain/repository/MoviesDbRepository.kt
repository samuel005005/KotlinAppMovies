package com.movies.db.movies.domain.repository

import com.movies.db.shared.domain.core.Resource
import com.movies.db.movies.domain.entities.Movie

interface MoviesDbRepository {
    suspend fun getNowPlaying(page: Int = 1): Resource<List<Movie>>
}