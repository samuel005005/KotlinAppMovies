package com.movies.db.infrastructure.data.remote

import com.movies.db.domain.entities.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesDbApi {
    @GET("now_playing")
    suspend fun getNowPlaying(
        @Query("page") limit: Int,
    ): List<Movie>
}