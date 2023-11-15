package com.movies.db.movies.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.movies.db.movies.presentation.MoviesViewModel

@Composable
fun HomeScreen(
    viewModel: MoviesViewModel = hiltViewModel()
) {

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