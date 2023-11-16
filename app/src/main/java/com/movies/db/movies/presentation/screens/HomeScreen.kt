package com.movies.db.movies.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.movies.db.movies.presentation.MoviesViewModel
import com.movies.db.movies.presentation.views.CarouselCard
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
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
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu Icon")
                }
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Menu Icon")
                }
            },
        )
    }) {
        if (state.value.isLoading) {
            CircularProgressIndicator()
        } else {
            CarouselCard(state.value.movies)
        }
    }

}

@Composable
fun HorizontalListView() {
    val items = (1..50).toList()

    LazyRow(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 20.dp),
        modifier = Modifier.height(380.dp)
    ) {
        items(items) { item ->
            Text(
                text = "Item $item",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .background(color = Color.LightGray)
                    .padding(16.dp)
            )
        }
    }
}