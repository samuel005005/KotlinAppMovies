package com.movies.db.movies.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.movies.db.R
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselCard() {

    val pagerState = rememberPagerState(initialPage = 2)
    val sliderList = listOf(
        "https://picsum.photos/id/237/800/500",
        "https://picsum.photos/id/237/800/500",
        "https://picsum.photos/id/237/800/500",
        "https://picsum.photos/id/237/800/500"
    )
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
//            IconButton(enabled = pagerState.currentPage > 0, onClick = {
//                scope.launch {
//                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
//                }
//            }) {
//                Icon(Icons.Default.KeyboardArrowLeft, null)
//            }
            HorizontalPager(
                count = sliderList.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 55.dp),
                modifier = Modifier
                    .height(250.dp)
                    .weight(1f)
            ) { page ->
                Card(shape = RoundedCornerShape(10.dp), modifier = Modifier.graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page = page).absoluteValue
                    lerp(
                        start = 0.85f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.5f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(sliderList[page])
                            .crossfade(true).scale(Scale.FILL).build(),
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.placeholder),
                        error = painterResource(id = R.drawable.error)
                    )
                }
            }
//            IconButton(enabled = pagerState.currentPage < pagerState.pageCount - 1, onClick = {
//                scope.launch {
//                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
//                }
//            }) {
//                Icon(Icons.Default.KeyboardArrowRight, null)
//            }
        }

        Row(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(sliderList.size) {
                val color =
                    if (pagerState.currentPage == it) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .size(10.dp)
                        .background(color)
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(it)
                            }
                        }
                ) {

                }
            }
        }
    }
}