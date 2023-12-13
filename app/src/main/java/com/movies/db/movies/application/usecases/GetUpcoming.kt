package com.movies.db.movies.application.usecases

import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.domain.repository.IMoviesDbRepository
import com.movies.db.app.core.Resource
import javax.inject.Inject

class GetUpcoming @Inject constructor(private val moviesDbRepository: IMoviesDbRepository) {
    suspend operator fun invoke(page: Int = 1, refresh: Boolean = false): Resource<List<Movie>> {
        return moviesDbRepository.getUpcoming(page, refresh)
    }
}