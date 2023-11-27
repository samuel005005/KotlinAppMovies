import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.movies.db.R
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.shared.domain.core.HumanFormats

@Composable
fun MovieWidget(movie: Movie, modifier: Modifier) {
    val yellowShade800 = Color(0xFFFFC107)

    Column(modifier = modifier) {
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

        }
        Box(modifier = Modifier.width(150.dp)) {
            Text(
                text = movie.title,
                maxLines = 2,
                style = MaterialTheme.typography.titleMedium,
                // You can customize the style based on your requirements
            )
        }
        Box(modifier = Modifier.height(5.dp))
        Box(modifier = Modifier.width(150.dp)) {
            Row(

            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = yellowShade800,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "${movie.voteAverage}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold, color = yellowShade800
                    ),
                )
            }
        }

    }

}