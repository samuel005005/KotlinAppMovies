import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.presentation.MoviesViewModel

@Composable
fun MoviesScreen(
    navHostController: NavHostController,
    movieId: Long
) {
    Text(text = movieId.toString())
}