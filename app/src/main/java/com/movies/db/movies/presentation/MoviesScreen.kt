import androidx.compose.runtime.Composable
import com.movies.db.movies.presentation.MoviesViewModel
import androidx.hilt.navigation.compose.hiltViewModel
@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel()
) {
}