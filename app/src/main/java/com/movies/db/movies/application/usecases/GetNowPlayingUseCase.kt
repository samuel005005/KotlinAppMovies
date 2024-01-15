package com.movies.db.movies.application.usecases

import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.domain.repository.IMoviesDbRepository
import com.movies.db.app.core.Resource
import com.movies.db.movies.domain.events.DomainEvent
import com.movies.db.movies.domain.events.EventManager
import javax.inject.Inject

class GetNowPlayingUseCase @Inject constructor(private val moviesDbRepository: IMoviesDbRepository) :
    DomainEvent {
    suspend operator fun invoke(page: Int = 1, refresh: Boolean = false): Resource<List<Movie>> {

        EventManager.postEvent(this)
        return moviesDbRepository.getNowPlaying(page, refresh)
    }

    override fun execute(eventData: Any) {
        TODO("Not yet implemented")
    }
}