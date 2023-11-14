package com.pokemon.moviesapp.infrastructure.datasources

import com.movies.db.domain.core.Resource
import com.movies.db.domain.datasources.MoviesDataSource
import com.movies.db.domain.entities.Movie
import com.movies.db.infrastructure.data.remote.MoviesDbApi
import javax.inject.Inject

class MoviesDataSource @Inject constructor(private val api: MoviesDbApi) : MoviesDataSource {
    override suspend fun getNowPlaying(page: Int): Resource<List<Movie>> {
        val movies = api.getNowPlaying(page)
        return Resource.Success(movies)
    }
}