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
        getNowPlaying(1)
    }

    fun getNowPlaying(page: Int, refresh: Boolean = false) {
        _nowPlayingMovies.value = MoviesState(isLoading = true)
        viewModelScope.launch {
            try {
                when (val result = getNowPlayingUseCase(page, refresh)) {
                    is Resource.Success -> {
                        _nowPlayingMovies.value = MoviesState(
                            isLoading = false,
                            movies = result.data ?: emptyList()
                        )
                        moviesSlideShow()
                    }

                    else -> {
                        _nowPlayingMovies.value = MoviesState(
                            isLoading = false,
                            error = result.message!!
                        )
                    }
                }
            } catch (e: Exception) {
                _nowPlayingMovies.value = MoviesState(
                    isLoading = false,
                    error = "${e.message}"
                )
            }
        }
    }

    private fun moviesSlideShow() {
        if (!_nowPlayingMovies.value.isLoading) {
            _carrouselMovies.value = MoviesState(
                isLoading = false, movies = _nowPlayingMovies.value.movies.subList(0, 6)
            )
        }
    }
}