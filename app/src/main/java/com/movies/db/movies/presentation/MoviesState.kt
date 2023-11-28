package com.movies.db.movies.presentation

import com.movies.db.movies.presentation.parcebles.entities.MovieParcelize


data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<MovieParcelize> = emptyList(),
    val error: String? = "",
    val endReached: Boolean = false,
    val page: Int = 1
)