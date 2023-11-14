package com.pokemon.moviesapp.presentation.movies

import com.pokemon.moviesapp.domain.entities.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie>? = null,
    val error: String = ""
)