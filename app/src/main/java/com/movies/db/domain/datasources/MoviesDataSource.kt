package com.movies.db.domain.datasources

import com.movies.db.domain.core.Resource
import com.movies.db.domain.entities.Movie

interface MoviesDataSource {
    suspend fun getNowPlaying(page: Int = 1): Resource<List<Movie>>
}