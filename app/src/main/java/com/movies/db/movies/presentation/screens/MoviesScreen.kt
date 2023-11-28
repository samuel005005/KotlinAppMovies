import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.movies.db.R
import com.movies.db.movies.presentation.parcebles.entities.MovieParcelize

@Composable
fun MoviesScreen(
    navHostController: NavHostController,
    movie: MovieParcelize
) {
    AsyncImage(
        contentScale = ContentScale.Crop,
        model = ImageRequest.Builder(LocalContext.current)
            .data(movie.posterPath).scale(Scale.FIT).build(),
        contentDescription = null,
        modifier = Modifier
            .height(500.dp),
        placeholder = painterResource(id = R.drawable.placeholder),
        error = painterResource(id = R.drawable.placeholder)
    )
}