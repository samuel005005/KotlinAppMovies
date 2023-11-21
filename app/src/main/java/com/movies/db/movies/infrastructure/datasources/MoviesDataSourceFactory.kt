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

    suspend fun getNowPlaying(): Resource<List<Movie>> {
        var movies = moviesCacheData.getNowPlaying();
        if (movies.data?.isEmpty() == true) {
            movies = moviesRemoteData.getNowPlaying()
            if (movies.data != null && movies.data!!.isNotEmpty()) {
                val movies = movies.data!!;
                moviesCacheData.saveMovies(movies = movies);
            }
        }
        return movies
    }

}