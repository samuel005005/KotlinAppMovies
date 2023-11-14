package com.pokemon.moviesapp.infrastructure.datasources

import com.moviesapp.domain.core.Resource
import com.moviesapp.domain.datasources.MoviesDataSource
import com.moviesapp.domain.entities.Movie
import com.pokemon.moviesapp.infrastructure.data.remote.MoviesDbApi
import javax.inject.Inject

class MoviesDataSource @Inject constructor(private val api: MoviesDbApi) : MoviesDataSource {
    override suspend fun getNowPlaying(page: Int): Resource<List<Movie>> {
        val movies = api.getNowPlaying(page)
        return Resource.Success(movies)
    }
}