package com.movies.db.movies.presentation

import com.movies.db.movies.domain.entities.Movie


data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "",
)