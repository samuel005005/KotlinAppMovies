package com.pokemon.moviesapp.domain.datasources

import com.pokemon.moviesapp.domain.entities.Movie

interface MoviesDataSource {
    suspend fun getNowPlaying(page: Int = 1): List<Movie>
}