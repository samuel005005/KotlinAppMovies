package com.movies.db.shared.presentation

import MoviesScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.movies.db.movies.presentation.MoviesViewModel
import com.movies.db.movies.presentation.screens.HomeScreen
import com.movies.db.shared.presentation.util.Routes

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.shareViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}


@Composable
fun NavHostApplication() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Home.screenRoute) {

        navigation(
            startDestination = Routes.Home.screenRoute, route = "movie"
        ) {

            composable(Routes.Home.screenRoute) {
                val viewModel = it.shareViewModel<MoviesViewModel>(navController = navController)
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
}