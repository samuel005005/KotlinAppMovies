package com.pokemon.moviesapp.aplicacion.usecases

import com.pokemon.moviesapp.domain.entities.Movie
import com.pokemon.moviesapp.domain.repository.MoviesDbRepository
import com.pokemon.moviesapp.domain.core.Resource
import javax.inject.Inject

class GetNowPlayingUseCase @Inject constructor(private val moviesDbRepository: MoviesDbRepository) {
    suspend operator fun invoke(page: Int): Resource<List<Movie>> {
        return moviesDbRepository.getNowPlaying(page)
    }
}