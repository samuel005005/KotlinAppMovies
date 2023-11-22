package com.movies.db.movies.infrastructure.datasources

import com.movies.db.movies.domain.datasources.MoviesDataSource
import com.movies.db.movies.infrastructure.data.cache.MovieEntity
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class MoviesCacheDataSource @Inject constructor(
    private val realm: Realm,
) : MoviesDataSource<RealmResults<MovieEntity>> {
    override suspend fun getNowPlaying(page: Int): RealmResults<MovieEntity> {
        return realm.where(MovieEntity::class.java).findAll()
    }
    suspend fun saveMovies(movies: List<MovieEntity>): Boolean {
        return suspendCoroutine { result ->
            realm.executeTransactionAsync({
                movies
            },
                { result.resumeWith(Result.success(true)) },
                { result.resumeWith(Result.success(false)) })
        }
    }
}