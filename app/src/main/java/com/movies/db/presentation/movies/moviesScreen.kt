import androidx.compose.runtime.Composable
import com.movies.db.presentation.movies.MoviesViewModel
import androidx.hilt.navigation.compose.hiltViewModel
@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel()
) {
}