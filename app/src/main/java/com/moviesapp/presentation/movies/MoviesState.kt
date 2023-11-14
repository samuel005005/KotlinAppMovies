package com.moviesapp.presentation.movies

import com.moviesapp.domain.entities.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie>? = null,
    val error: String = ""
)