package com.movies.db.movies.domain.datasources

import com.movies.db.movies.domain.entities.Movie

interface MoviesDataSource<T> {
    suspend fun getNowPlaying(page: Int): T
}