import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun MovieWidget(movie: Movie) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .padding(horizontal = 8.dp)
    ) {
        AsyncImage(

            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current).data(movie.posterPath)
                .crossfade(true).scale(Scale.FILL).build(),
            contentDescription = null,
            modifier = Modifier
                .height(220.dp)
                .clip(RoundedCornerShape(8.dp)),
            placeholder = painterResource(id = R.drawable.loading),
            error = painterResource(id = R.drawable.error),

            )
        Box {
            Text(
                text = movie.title,
                maxLines = 2,
                style = MaterialTheme.typography.headlineLarge,
                // You can customize the style based on your requirements
            )
        }
    }
}