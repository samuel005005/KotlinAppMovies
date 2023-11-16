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

    private val _state = MutableStateFlow(MoviesState())
    val state: StateFlow<MoviesState> = _state.asStateFlow()

    init {
        moviesSlideShow(1)
    }


    private fun moviesSlideShow(page: Int) {
        _state.value = MoviesState(isLoading = true)
        viewModelScope.launch {
            try {
                when (val result = getNowPlayingUseCase(page)) {
                    is Resource.Success -> {

                        _state.value = MoviesState(
                            isLoading = false,
                            movies = result.data?.subList(0, 6)
                        )
                    }

                    else -> {
                        _state.value = MoviesState(
                            isLoading = false,
                            error = result.message!!
                        )
                    }
                }
            } catch (e: Exception) {
                _state.value = MoviesState(
                    isLoading = false,
                    error = "${e.message}"
                )
            }

        }
    }
}