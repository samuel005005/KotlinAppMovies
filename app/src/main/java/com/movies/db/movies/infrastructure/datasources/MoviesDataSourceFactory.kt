package com.movies.db.movies.infrastructure.datasources

import com.movies.db.movies.domain.datasources.MoviesCacheDataSource
import com.movies.db.movies.domain.datasources.MoviesRemoteDataSource
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.shared.domain.core.Resource
import javax.inject.Inject

class MoviesDataSourceFactory @Inject constructor(
    private val moviesRemoteData: MoviesRemoteDataSource,
    private val moviesCacheData: MoviesCacheDataSource,
) {
    suspend fun getNowPlaying(page: Int, refresh: Boolean): List<Movie> {
        var movies = moviesCacheData.getNowPlaying();
        if (movies.isEmpty() || refresh) {
            movies = moviesRemoteData.getNowPlaying(page)!!
            if (movies.isEmpty()) {
                moviesCacheData.saveMovies(movies = movies);
            }
        }
        return movies
    }

}