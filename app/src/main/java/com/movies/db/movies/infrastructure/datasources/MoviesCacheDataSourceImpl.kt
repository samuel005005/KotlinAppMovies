package com.movies.db.movies.infrastructure.datasources

import com.movies.db.movies.domain.datasources.MoviesCacheDataSource
import com.movies.db.movies.infrastructure.data.cache.MovieEntity
import io.realm.Realm
import io.realm.RealmResults
import java.util.Calendar
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class MoviesCacheDataSourceImpl @Inject constructor(
    private val realm: Realm,
) : MoviesCacheDataSource {
    override suspend fun getNowPlaying(page: Int): RealmResults<MovieEntity> {
        return realm.where(MovieEntity::class.java).lessThan("popularity", 1000).findAll()
    }

    override suspend fun getPopular(page: Int): RealmResults<MovieEntity> {
        return realm.where(MovieEntity::class.java).greaterThan("popularity", 1000).findAll()
    }

    override suspend fun getTopRated(page: Int): RealmResults<MovieEntity> {
        return realm.where(MovieEntity::class.java).greaterThan("vote_average", 7.0).findAll()
    }

    override suspend fun getUpcoming(page: Int): RealmResults<MovieEntity> {
        return realm.where(MovieEntity::class.java)
            .greaterThan("release_date", Calendar.getInstance().time).findAll()
    }

    override suspend fun saveMovies(movies: List<MovieEntity>): Boolean {
        return suspendCoroutine { result ->
            realm.executeTransactionAsync({
                movies
            },
                { result.resumeWith(Result.success(true)) },
                { result.resumeWith(Result.success(false)) })
        }
    }

    override suspend fun validateExistMovie(movie: MovieEntity): Boolean {
        return realm.where(MovieEntity::class.java).equalTo("id", movie.id).findFirst() !== null
    }
}