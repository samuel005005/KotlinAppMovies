package com.movies.db.movies.domain.datasources

import com.movies.db.movies.infrastructure.data.cache.MovieEntity
import io.realm.RealmResults

interface IMoviesCacheDataSource : IMoviesData<RealmResults<MovieEntity>> {
    suspend fun saveMovies(movies: List<MovieEntity>): Boolean
    suspend fun validateExistMovie(movie: MovieEntity): Boolean
}
