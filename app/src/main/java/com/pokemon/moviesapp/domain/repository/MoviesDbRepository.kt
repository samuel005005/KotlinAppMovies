package com.pokemon.moviesapp.domain.repository

import com.pokemon.moviesapp.domain.entities.Movie

interface MoviesDbRepository {
    suspend fun getNowPlaying(page: Int = 1): List<Movie>
}