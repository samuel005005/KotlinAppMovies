package com.movies.db.aplicacion.usecases

import com.movies.db.domain.entities.Movie
import com.movies.db.domain.repository.MoviesDbRepository
import com.movies.db.domain.core.Resource
import javax.inject.Inject

class GetNowPlayingUseCase @Inject constructor(private val moviesDbRepository: MoviesDbRepository) {
    suspend operator fun invoke(page: Int): Resource<List<Movie>> {
        return moviesDbRepository.getNowPlaying(page)
    }
}