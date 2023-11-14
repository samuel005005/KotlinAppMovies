package com.moviesapp.aplicacion.usecases

import com.moviesapp.domain.entities.Movie
import com.moviesapp.domain.repository.MoviesDbRepository
import com.moviesapp.domain.core.Resource
import javax.inject.Inject

class GetNowPlayingUseCase @Inject constructor(private val moviesDbRepository: MoviesDbRepository) {
    suspend operator fun invoke(page: Int): Resource<List<Movie>> {
        return moviesDbRepository.getNowPlaying(page)
    }
}