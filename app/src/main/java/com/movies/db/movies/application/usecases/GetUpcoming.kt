package com.movies.db.movies.application.usecases

import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.domain.repository.MoviesDbRepository
import com.movies.db.shared.domain.core.Resource
import javax.inject.Inject

class GetUpcoming @Inject constructor(private val moviesDbRepository: MoviesDbRepository) {
    suspend operator fun invoke(page: Int = 1, refresh: Boolean = false): Resource<List<Movie>> {
        return moviesDbRepository.getUpcoming(page, refresh)
    }
}