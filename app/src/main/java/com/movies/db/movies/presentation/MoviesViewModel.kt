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


    val paginator = DefaultPaginator(
        initialKey = _nowPlayingMovies.value.page,
        onLoadUpdated = {
            _nowPlayingMovies.value = _nowPlayingMovies.value.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            getNowPlayingUseCase(nextPage, true)
        },
        getNextKey = {
            _nowPlayingMovies.value.page + 1
        },
        onError = {
            _nowPlayingMovies.value = _nowPlayingMovies.value.copy(error = it?.localizedMessage)
        },
        onSuccess = { movies, newPage ->
            _nowPlayingMovies.value = _nowPlayingMovies.value.copy(
                movies = _nowPlayingMovies.value.movies + movies,
                page = newPage,
                endReached = movies.isNotEmpty(),
            )
        }
    )

    init {
        getNowPlaying()
    }

    fun getNowPlaying() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

}