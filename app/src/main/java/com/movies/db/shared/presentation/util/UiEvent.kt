package com.movies.db.presentation.util

import com.movies.db.shared.presentation.util.Event


sealed class UiEvent : Event() {

    data class ShowSnackBar(val message: String) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    data class Data(val data: Any) : UiEvent()
    object NavigateUp : UiEvent()
    object Deny : UiEvent()
    object OnLogin : UiEvent()
    object OnLogout : UiEvent()

}