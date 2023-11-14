package com.pokemon.moviesapp.infrastructure.data.remote

import com.pokemon.moviesapp.domain.entities.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesDbApi {
    @GET("pokemon")
    suspend fun getNowPlaying(
        @Query("page") limit: Int,
    ): List<Movie>
}