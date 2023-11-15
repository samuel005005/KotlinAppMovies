package com.movies.db.movies.infrastructure.data.remote


import com.movies.db.movies.infrastructure.data.remote.responses.MoviesDbResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.Response
interface MoviesDbApi {
    @Headers("Accept: application/json")
    @GET("now_playing")
    suspend fun getNowPlaying(
        @Header("Authorization") authorization: String,
        @Query("page") page: Int,
    ): Response<MoviesDbResponse>
}