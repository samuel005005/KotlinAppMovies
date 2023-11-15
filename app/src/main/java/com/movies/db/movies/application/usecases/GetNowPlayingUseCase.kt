package com.movies.db.movies.application.usecases

import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.domain.repository.MoviesDbRepository
import com.movies.db.shared.domain.core.Resource
import javax.inject.Inject

class GetNowPlayingUseCase @Inject constructor(private val moviesDbRepository: MoviesDbRepository) {
    suspend operator fun invoke(page: Int): Resource<List<Movie>> {
        return moviesDbRepository.getNowPlaying(page)
    }
}