package com.pokemon.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pokemon.moviesapp.presentation.theme.MoviesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HorizontalListView()
                }
            }
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