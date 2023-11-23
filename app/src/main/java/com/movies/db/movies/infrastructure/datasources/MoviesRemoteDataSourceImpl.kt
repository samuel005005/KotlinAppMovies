package com.movies.db.movies.infrastructure.datasources

import com.movies.db.movies.domain.datasources.MovieRemoteDataSource
import com.movies.db.movies.infrastructure.data.remote.MoviesDbApi
import com.movies.db.movies.infrastructure.data.remote.responses.MoviesDbResponse
import com.movies.db.shared.infrastructure.config.util.Constants.THE_MOVIEDB_KEY
import retrofit2.Response
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val api: MoviesDbApi
) : MovieRemoteDataSource {
    override suspend fun getNowPlaying(page: Int): Response<MoviesDbResponse> {
        return api.getNowPlaying(authorization = "Bearer $THE_MOVIEDB_KEY", page)
    }

    override suspend fun getPopular(page: Int): Response<MoviesDbResponse> {
        return api.getPopular(authorization = "Bearer $THE_MOVIEDB_KEY", page)
    }

    override suspend fun getTopRated(page: Int): Response<MoviesDbResponse> {
        return api.getTopRated(authorization = "Bearer $THE_MOVIEDB_KEY", page)
    }

    override suspend fun getUpcoming(page: Int): Response<MoviesDbResponse> {
        return api.getUpcoming(authorization = "Bearer $THE_MOVIEDB_KEY", page)
    }
}