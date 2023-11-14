package com.moviesapp.infrastructure.data.remote.responses

import com.pokemon.moviesapp.infrastructure.data.remote.responses.MovieMovieDB

data class MovieDbResponse(
    val dates: Dates,
    val page: Int,
    val results: List<MovieMovieDB>,
    val total_pages: Int,
    val total_results: Int
)