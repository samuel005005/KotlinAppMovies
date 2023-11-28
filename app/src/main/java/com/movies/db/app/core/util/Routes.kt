package com.movies.db.app.core.util

import com.movies.db.app.navigation.ParcelableNav
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.presentation.parcebles.entities.MovieParcelize

sealed class Routes(var screenRoute: String) {
    object Home : Routes("HomeScreen")
    object MovieDetails : Routes("MovieScreen/{${NavArgs.Item.key}}"), ParcelableNav {
        fun createRoute(movie: MovieParcelize): String {
            return "MovieScreen/${encodeValue(movie)}"
        }
    }
}

enum class NavArgs(val key: String) {
    Item("Movie")
}