package com.pokemon.moviesapp.infrastructure.repositories

import com.pokemon.moviesapp.domain.core.Resource
import com.pokemon.moviesapp.domain.datasources.MoviesDataSource
import com.pokemon.moviesapp.domain.entities.Movie
import com.pokemon.moviesapp.domain.repository.MoviesDbRepository
import javax.inject.Inject

class MoviesDbRepository @Inject constructor(private val moviesDataSource: MoviesDataSource) :
    MoviesDbRepository {
    override suspend fun getNowPlaying(page: Int): Resource<List<Movie>> {
        return moviesDataSource.getNowPlaying(page)
    }
}