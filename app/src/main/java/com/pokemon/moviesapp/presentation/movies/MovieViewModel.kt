package com.pokemon.moviesapp.presentation.movies

import androidx.compose.runtime.mutableStateOf
import com.pokemon.moviesapp.aplicacion.usecases.GetNowPlayingUseCase
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import com.pokemon.moviesapp.domain.core.Resource

@HiltViewModel
class MovieViewModel @Inject constructor(private val getNowPlayingUseCase: GetNowPlayingUseCase) :
    ViewModel() {
    private var _state = mutableStateOf(MoviesState())
    val state: State<MoviesState> = _state

    private fun getNowPlaying(page: Int) {
        viewModelScope.launch {
            val resutl = getNowPlayingUseCase(page)
            when (resutl) {
                is Resource.Success -> {
                    _state.value = MoviesState()
                }

                else -> {}
            }
        }
    }
}