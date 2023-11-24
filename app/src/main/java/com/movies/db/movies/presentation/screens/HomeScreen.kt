package com.movies.db.movies.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.movies.db.R
import com.movies.db.movies.presentation.MoviesViewModel
import com.movies.db.movies.presentation.widgets.CarouselCard
import com.movies.db.movies.presentation.widgets.MovieHorizontalListWidget

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MoviesViewModel = hiltViewModel()
) {

    val nowPlayingMovies = viewModel.nowPlayingMovies.collectAsStateWithLifecycle()
    val popularMovies = viewModel.popularMovies.collectAsStateWithLifecycle()

//    LaunchedEffect(key1 = true) {
//        viewModel.eventFlow.collectLatest { event ->
//            when (event) {
//                is UiEvent.ShowSnackbar -> {
//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = event.message
//                    )
//                }
//
//                else -> {
//                    Log.i("EventUI", "$event")
//                }
//            }
//        }
//    }

//    DisposableEffect(
//        key1 = lifecycleOwner,
//        effect = {
//            val observer = LifecycleEventObserver { _, event ->
//                if (event == Lifecycle.Event.ON_START) {
//                    permissionsState.launchMultiplePermissionRequest()
//                }
//            }
//            lifecycleOwner.lifecycle.addObserver(observer)
//
//            onDispose {
//                lifecycleOwner.lifecycle.removeObserver(observer)
//            }
//        }
//    )

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Cinemapedia")
            },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(R.drawable.movie),
                        contentDescription = stringResource(id = R.string.app_name)
                    )

                }
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Menu Icon")
                }
            },
        )
    }) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            if (nowPlayingMovies.value.movies.isNotEmpty()) {
                CarouselCard(nowPlayingMovies.value.movies.subList(0, 6))
                MovieHorizontalListWidget(
                    state = nowPlayingMovies.value,
                    title = "In Theaters",
                    subTitle = "On Monday 20",
                    fetchMoreMovies = {
                        viewModel.getNowPlaying()
                    }
                )
                MovieHorizontalListWidget(
                    state = popularMovies.value,
                    title = "Popular",
                    subTitle = "In this month",
                    fetchMoreMovies = {
                        viewModel.getPopular()
                    }
                )
            }
        }
    }
}


