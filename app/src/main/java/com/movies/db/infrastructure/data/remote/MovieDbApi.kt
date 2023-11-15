package com.movies.db.infrastructure.data.remote

import com.movies.db.domain.entities.Movie
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MoviesDbApi {
    @GET("now_playing")
    suspend fun getNowPlaying(
        @Header("Authorization") authorization: String,
        @Query("page") limit: Int,
    ): List<Movie>
}