package com.movies.db.movies.infrastructure.datasources

import com.movies.db.movies.domain.datasources.MoviesDataSource
import com.movies.db.movies.infrastructure.data.cache.MovieEntity
import com.movies.db.movies.infrastructure.data.remote.responses.MoviesDbResponse
import io.realm.RealmResults
import retrofit2.Response
import javax.inject.Inject

class MoviesDataSourceFactory @Inject constructor(
    private val moviesRemoteData: MoviesRemoteDataSource,
    private val moviesCacheData: MoviesCacheDataSource,
) {
    fun getRemoteData(): MoviesDataSource<Response<MoviesDbResponse>> {
        return moviesRemoteData
    }

    fun getCacheData(): MoviesDataSource<RealmResults<MovieEntity>> {
        return moviesCacheData
    }

}