package com.pokemon.moviesapp.domain.repository

import com.pokemon.moviesapp.domain.core.Resource
import com.pokemon.moviesapp.domain.entities.Movie

interface MoviesDbRepository {
    suspend fun getNowPlaying(page: Int = 1): Resource<List<Movie>>
}