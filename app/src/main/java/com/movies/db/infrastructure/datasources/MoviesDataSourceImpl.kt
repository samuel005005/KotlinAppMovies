package com.movies.db.infrastructure.datasources

import com.movies.db.domain.core.Resource
import com.movies.db.domain.datasources.MoviesDataSource
import com.movies.db.domain.entities.Movie
import com.movies.db.infrastructure.config.util.Constants.THE_MOVIEDB_KEY
import com.movies.db.infrastructure.data.remote.MoviesDbApi
import javax.inject.Inject

class MoviesDataSourceImpl @Inject constructor(private val api: MoviesDbApi) : MoviesDataSource {
    override suspend fun getNowPlaying(page: Int): Resource<List<Movie>> {
        val movies = api.getNowPlaying(authorization = "Basic $THE_MOVIEDB_KEY", page)
        return Resource.Success(movies)
    }
}