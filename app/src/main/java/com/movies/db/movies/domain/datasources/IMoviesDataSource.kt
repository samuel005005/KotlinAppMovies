package com.movies.db.movies.domain.datasources

import com.movies.db.app.core.Resource
import com.movies.db.movies.domain.entities.Movie

interface IMoviesDataSource {
    suspend fun getNowPlaying(page: Int, refresh: Boolean): Resource<List<Movie>>
    suspend fun getPopular(page: Int, refresh: Boolean): Resource<List<Movie>>
    suspend fun getTopRated(page: Int, refresh: Boolean): Resource<List<Movie>>
    suspend fun getUpcoming(page: Int, refresh: Boolean): Resource<List<Movie>>
}