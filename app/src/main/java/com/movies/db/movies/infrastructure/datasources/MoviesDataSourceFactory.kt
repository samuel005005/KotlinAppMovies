package com.movies.db.movies.infrastructure.datasources

import com.movies.db.movies.domain.datasources.MovieRemoteDataSource
import com.movies.db.movies.domain.datasources.MoviesCacheDataSource
import com.movies.db.movies.domain.datasources.MoviesDataSource
import com.movies.db.movies.infrastructure.data.cache.MovieEntity
import io.realm.RealmResults
import javax.inject.Inject

class MoviesDataSourceFactory @Inject constructor(
    private val moviesRemoteData: MovieRemoteDataSource,
    private val moviesCacheData: MoviesCacheDataSource,
) {
    fun getRemoteData(): MovieRemoteDataSource {
        return moviesRemoteData
    }

    fun getCacheData(): MoviesCacheDataSource {
        return moviesCacheData
    }

}