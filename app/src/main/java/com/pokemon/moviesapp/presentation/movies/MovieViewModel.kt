package com.pokemon.moviesapp.presentation.movies

import androidx.compose.runtime.mutableStateOf
import com.pokemon.moviesapp.domain.usecases.GetNowPlayingUseCase
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.State

@HiltViewModel
class MovieViewModel @Inject constructor(private val getNowPlayingUseCase: GetNowPlayingUseCase) :
    ViewModel() {
    private val _state = mutableStateOf(MoviesState())
    val state: State<MoviesState> = _state

    private fun getNowPlaying(page: Int) {
        viewModelScope.launch {
            getNowPlayingUseCase(page).onEach { result ->
                result.adult

            }
        }
    }
}