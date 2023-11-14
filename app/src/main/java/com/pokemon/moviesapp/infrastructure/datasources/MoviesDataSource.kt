package com.pokemon.moviesapp.infrastructure.datasources

import com.pokemon.moviesapp.domain.datasources.MoviesDataSource
import com.pokemon.moviesapp.domain.entities.Movie
import com.pokemon.moviesapp.infrastructure.data.remote.MoviesDbApi
import javax.inject.Inject

class MoviesDataSource @Inject constructor(private val api: MoviesDbApi) : MoviesDataSource {
    override suspend fun getNowPlaying(page: Int): List<Movie> {
        return api.getNowPlaying(page)
    }
}