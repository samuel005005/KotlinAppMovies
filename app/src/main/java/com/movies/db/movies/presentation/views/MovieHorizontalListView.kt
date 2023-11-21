package com.movies.db.movies.presentation.views

import MovieView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.presentation.MoviesState


@Composable
fun MovieHorizontalListView(
    movies: List<Movie>,
    title: String,
    subTitle: String,
    fetchMoreMovies: (page: Int) -> Unit,
) {
    val listState = rememberLazyListState()
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontSize = 20.sp)
        FilledTonalButton(onClick = {}) {
            Text(text = subTitle)
        }
    }
    Text("${listState.firstVisibleItemIndex}")
    LazyRow(state = listState) {
        items(movies.size) {
            MovieView(movie = movies[it])
        }
    }

}