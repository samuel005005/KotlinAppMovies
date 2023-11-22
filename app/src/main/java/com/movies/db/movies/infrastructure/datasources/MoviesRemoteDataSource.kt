package com.movies.db.movies.infrastructure.datasources

import com.movies.db.movies.domain.datasources.MoviesDataSource
import com.movies.db.movies.infrastructure.data.remote.MoviesDbApi
import com.movies.db.movies.infrastructure.data.remote.responses.MoviesDbResponse
import com.movies.db.shared.infrastructure.config.util.Constants.THE_MOVIEDB_KEY
import retrofit2.Response
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val api: MoviesDbApi
) : MoviesDataSource<Response<MoviesDbResponse>> {
    override suspend fun getNowPlaying(page: Int): Response<MoviesDbResponse> {
        return api.getNowPlaying(authorization = "Bearer $THE_MOVIEDB_KEY", page)

    }
}