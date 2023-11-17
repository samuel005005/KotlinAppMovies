package com.movies.db.movies.domain.datasources

import com.movies.db.movies.domain.entities.Movie
import com.movies.db.shared.domain.core.Resource

interface MoviesCacheDataSource {
    suspend fun getNowPlaying(): Resource<List<Movie>>
}