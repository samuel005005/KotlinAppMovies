package com.movies.db.movies.infrastructure.datasources

import com.movies.db.movies.domain.datasources.IMovieRemoteDataSource
import com.movies.db.movies.domain.datasources.IMoviesCacheDataSource
import javax.inject.Inject

class MoviesDataSourceFactory @Inject constructor(
    private val moviesRemoteData: IMovieRemoteDataSource,
    private val moviesCacheData: IMoviesCacheDataSource,
) {
    fun getRemoteData(): IMovieRemoteDataSource {
        return moviesRemoteData
    }

    fun getCacheData(): IMoviesCacheDataSource {
        return moviesCacheData
    }

}