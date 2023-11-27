package com.movies.db.movies.application.usecases

import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.domain.repository.MoviesDbRepository
import com.movies.db.app.core.Resource
import javax.inject.Inject

class GetTopRated @Inject constructor(private val moviesDbRepository: MoviesDbRepository) {
    suspend operator fun invoke(page: Int = 1, refresh: Boolean = false): Resource<List<Movie>> {
        return moviesDbRepository.getTopRated(page, refresh)
    }
}