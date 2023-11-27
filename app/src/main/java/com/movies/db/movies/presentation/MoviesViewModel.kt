package com.movies.db.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movies.db.app.core.Resource
import com.movies.db.app.core.util.DefaultPaginator
import com.movies.db.movies.application.usecases.GetNowPlayingUseCase
import com.movies.db.movies.application.usecases.GetPopularUseCase
import com.movies.db.movies.application.usecases.GetTopRated
import com.movies.db.movies.application.usecases.GetUpcoming
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.presentation.parcelables.mappers.MovieToViewMovieMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val getPopularUseCase: GetPopularUseCase,
    private val getTopRated: GetTopRated,
    private val getUpcoming: GetUpcoming
) :
    ViewModel() {
    private val movieToViewMovieMapper = MovieToViewMovieMapper()
    private val _nowPlayingMovies = MutableStateFlow(MoviesState())
    val nowPlayingMovies: StateFlow<MoviesState> = _nowPlayingMovies.asStateFlow()
    private val _popularMovies = MutableStateFlow(MoviesState())
    val popularMovies: StateFlow<MoviesState> = _popularMovies.asStateFlow()
    private val _topRatedMovies = MutableStateFlow(MoviesState())
    val topRatedMovies: StateFlow<MoviesState> = _topRatedMovies.asStateFlow()
    private val _upcomingMovies = MutableStateFlow(MoviesState())
    val upComingMovies: StateFlow<MoviesState> = _upcomingMovies.asStateFlow()

    init {
        getNowPlaying()
        getPopular()
        getUpcomingMovies()
        getTopRatedMovies()
    }

    fun getNowPlaying() {
        viewModelScope.launch {
            paginator(states = _nowPlayingMovies, useCase =
            { nextPage, refresh ->
                getNowPlayingUseCase(nextPage, refresh)
            }).loadNextItems()
        }
    }

    fun getPopular() {
        viewModelScope.launch {
            paginator(states = _popularMovies, useCase =
            { nextPage, refresh ->
                getPopularUseCase(nextPage, refresh)
            }).loadNextItems()
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            paginator(states = _topRatedMovies, useCase =
            { nextPage, refresh ->
                getTopRated(nextPage, refresh)
            }).loadNextItems()
        }
    }

    fun getUpcomingMovies() {
        viewModelScope.launch {
            paginator(states = _upcomingMovies, useCase =
            { nextPage, refresh ->
                getUpcoming(nextPage, refresh)
            }).loadNextItems()
        }
    }

    private fun paginator(
        states: MutableStateFlow<MoviesState>,
        useCase: suspend (nextKey: Int, refresh: Boolean) -> Resource<List<Movie>>,
    ): DefaultPaginator<Int, Movie> {
        return DefaultPaginator(
            initialKey = states.value.page,
            onLoadUpdated = {
                states.value = states.value.copy(isLoading = it)
            },
            onRequest = { nextPage ->
                useCase(nextPage, true)
            },
            getNextKey = {
                states.value.page + 1
            },
            onError = {
                states.value = states.value.copy(error = it?.localizedMessage)
            },
            onSuccess = { movies, newPage ->
                states.value = states.value.copy(
                    movies = states.value.movies + movies.map { movieToViewMovieMapper.map(it) },
                    page = newPage,
                    endReached = movies.isNotEmpty(),
                )
            }
        )
    }
}