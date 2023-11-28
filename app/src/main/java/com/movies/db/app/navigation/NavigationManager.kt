package com.movies.db.app.navigation

import MoviesScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.movies.db.app.core.util.NavArgs
import com.movies.db.app.core.util.Routes
import com.movies.db.movies.presentation.parcebles.entities.MovieParcelize
import com.movies.db.movies.presentation.screens.HomeScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavGraphApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Home.screenRoute) {
        composable(Routes.Home.screenRoute) {
            HomeScreen(navHostController = navController)
        }
        composable(
            route = Routes.MovieDetails.screenRoute,
            arguments = listOf(navArgument(NavArgs.Item.key) {
                type = NavType.parcelableTypeOf<MovieParcelize>()
            })
        ) {
            val movie = it.arguments?.getParcelable("Movie", MovieParcelize::class.java)
            MoviesScreen(
                navHostController = navController,
                movie = movie!!
            )
        }
    }
}