package com.movies.db.app.navigation

import MoviesScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.movies.db.app.core.util.NavArgs
import com.movies.db.app.core.util.Routes
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.presentation.screens.HomeScreen

@Composable
fun NavGraphApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Home.screenRoute) {
        composable(Routes.Home.screenRoute) {
            HomeScreen(navHostController = navController)
        }
        composable(
            route = Routes.MovieDetails.screenRoute,
            arguments = listOf(navArgument(NavArgs.Item.key) {
                type = NavType.parcelableTypeOf<Movie>()
            })
        ) {
            val movie = it.arguments?.getParcelable<Movie>("movie")
            MoviesScreen(
                navHostController = navController,
                movie= movie!!
            )
        }
    }
}