package com.movies.db.movies.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.movies.db.movies.application.usecases.GetNowPlayingUseCase
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.shared.domain.core.Resource
import com.movies.db.shared.presentation.util.DefaultPaginator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getNowPlayingUseCase: GetNowPlayingUseCase) :
    ViewModel() {

    private val _carrouselMovies = MutableStateFlow(MoviesState())
    val carrouselMovies: StateFlow<MoviesState> = _carrouselMovies.asStateFlow()
    private val _nowPlayingMovies = MutableStateFlow(MoviesState())
    val nowPlayingMovies: StateFlow<MoviesState> = _nowPlayingMovies.asStateFlow()


    init {
        getNowPlaying()
    }

    fun getNowPlaying() {
        viewModelScope.launch {
            paginator(states = _nowPlayingMovies, useCase =
            { nextPage, refresh ->
                getNowPlayingUseCase(nextPage, refresh)
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
                    movies = states.value.movies + movies,
                    page = newPage,
                    endReached = movies.isNotEmpty(),
                )
            }
        )
    }
}