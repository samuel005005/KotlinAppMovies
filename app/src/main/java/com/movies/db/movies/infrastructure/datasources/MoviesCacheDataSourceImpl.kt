package com.movies.db.movies.infrastructure.datasources

import com.movies.db.movies.domain.datasources.MoviesCacheDataSource
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.infrastructure.data.cache.MovieEntity
import com.movies.db.movies.infrastructure.mappers.MovieToEntityMapper
import com.movies.db.shared.domain.core.Resource
import com.movies.db.shared.infrastructure.config.util.Constants
import io.realm.Realm
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class MoviesCacheDataSourceImpl @Inject constructor(
    private val realm: Realm,
    private val movieMapper: MovieToEntityMapper,
) : MoviesCacheDataSource {
    override suspend fun getNowPlaying(): List<Movie> {
        val data = realm.where(MovieEntity::class.java).findAll()
        return data.map { movieMapper.reverseMap(it) }
    }

    override suspend fun saveMovies(movies: List<Movie>): Boolean {
        return suspendCoroutine { result ->
            realm.executeTransactionAsync({ bgRealm ->
                movies.forEach {
                    bgRealm.insertOrUpdate(movieMapper.map(it))
                }
            },
                { result.resumeWith(Result.success(true)) },
                { result.resumeWith(Result.success(false)) })
        }
    }
}