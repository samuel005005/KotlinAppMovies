package com.movies.db.presentation.movies

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.movies.db.aplicacion.usecases.GetNowPlayingUseCase
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import com.movies.db.domain.core.Resource

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getNowPlayingUseCase: GetNowPlayingUseCase) :
    ViewModel() {
    private var _state = mutableStateOf(MoviesState())
    var state: State<MoviesState> = _state

    fun getNowPlaying(page: Int) {
        viewModelScope.launch {
            when (val result = getNowPlayingUseCase(page)) {
                is Resource.Success -> {
                    Log.i("RESULT_MOVIES", "${result.data}")
                    _state.value = MoviesState()
                }

                else -> {
                    Log.i("TEST", "TEST")
                }
            }
        }
    }
}