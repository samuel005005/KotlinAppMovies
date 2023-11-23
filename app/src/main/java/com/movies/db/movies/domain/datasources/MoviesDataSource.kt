package com.movies.db.movies.domain.datasources

import com.movies.db.movies.domain.entities.Movie
import com.movies.db.shared.domain.core.Resource

interface MoviesDataSource<T> {
    suspend fun getNowPlaying(page: Int): T
    suspend fun getPopular(page: Int): T
    suspend fun getTopRated(page: Int): T
    suspend fun getUpcoming(page: Int): T

}