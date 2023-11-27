package com.movies.db.app.navigation

import MoviesScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.movies.db.app.core.util.Routes
import com.movies.db.movies.presentation.screens.HomeScreen

@Composable
fun NavGraphApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Home.screenRoute) {
        composable(Routes.Home.screenRoute) {
            HomeScreen(navHostController = navController)
        }
        composable(Routes.Movie.screenRoute, arguments = listOf(navArgument("movieId") {
            type = NavType.LongType
        })) { backStackEntry ->
            MoviesScreen(
                navHostController = navController,
                movieId = backStackEntry.arguments?.getLong("movieId") ?: 0
            )
        }
    }
}