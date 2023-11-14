package com.moviesapp.domain.repository

import com.moviesapp.domain.core.Resource
import com.moviesapp.domain.entities.Movie

interface MoviesDbRepository {
    suspend fun getNowPlaying(page: Int = 1): Resource<List<Movie>>
}