import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.movies.db.R
import com.movies.db.movies.domain.entities.Movie

@Composable
fun MovieView(movie: Movie) {
    Box(modifier = Modifier
        .width(150.dp)
        .padding(10.dp)
    ) {
        AsyncImage(
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current).data(movie.posterPath)
                .crossfade(true).scale(Scale.FILL).build(),
            contentDescription = null,
            modifier = Modifier.height(220.dp),
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder)
        )
    }
}