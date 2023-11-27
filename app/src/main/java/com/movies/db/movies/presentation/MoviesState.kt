package com.movies.db.movies.presentation

import com.movies.db.movies.presentation.parcelables.entities.MovieDataView


data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<MovieDataView> = emptyList(),
    val error: String? = "",
    val endReached: Boolean = false,
    val page: Int = 1
)