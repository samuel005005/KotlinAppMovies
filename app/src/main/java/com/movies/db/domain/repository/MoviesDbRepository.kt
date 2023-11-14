package com.movies.db.domain.repository

import com.movies.db.domain.core.Resource
import com.movies.db.domain.entities.Movie

interface MoviesDbRepository {
    suspend fun getNowPlaying(page: Int = 1): Resource<List<Movie>>
}