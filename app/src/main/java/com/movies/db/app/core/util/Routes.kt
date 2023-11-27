package com.movies.db.app.core.util

sealed class Routes(var screenRoute: String) {
    object Home : Routes("HomeScreen")
    object Movie : Routes("MovieScreen/{movieId}") {
        fun createRoute(movieId: Long): String {
            return "MovieScreen/$movieId"
        }
    }
}
