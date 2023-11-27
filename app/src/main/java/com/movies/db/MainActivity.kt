package com.movies.db

import MoviesScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.movies.db.app.core.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
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
    }
}


