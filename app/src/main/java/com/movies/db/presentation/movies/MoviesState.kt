package com.movies.db.presentation.movies

import com.movies.db.domain.entities.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie>? = null,
    val error: String = ""
)