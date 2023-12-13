package com.movies.db.movies.domain.datasources

interface IMoviesData<T> {
    suspend fun getNowPlaying(page: Int): T
    suspend fun getPopular(page: Int): T
    suspend fun getTopRated(page: Int): T
    suspend fun getUpcoming(page: Int): T

}