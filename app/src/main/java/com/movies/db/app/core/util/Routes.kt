package com.movies.db.app.core.util

import com.movies.db.app.navigation.ParcelableNav
import com.movies.db.movies.domain.entities.Movie

sealed class Routes(var screenRoute: String) {
    object Home : Routes("HomeScreen")
    object MovieDetails : Routes("MovieScreen/{${NavArgs.Item.key}}"), ParcelableNav {
        fun createRoute(movie: Movie): String {
            return "MovieScreen/${encodeValue(movie)}"
        }
    }
}

enum class NavArgs(val key: String) {
    Item("Movie")
}