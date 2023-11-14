package com.moviesapp.domain.datasources

import com.moviesapp.domain.core.Resource
import com.moviesapp.domain.entities.Movie

interface MoviesDataSource {
    suspend fun getNowPlaying(page: Int = 1): Resource<List<Movie>>
}