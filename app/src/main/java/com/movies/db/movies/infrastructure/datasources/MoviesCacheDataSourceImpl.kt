package com.movies.db.movies.infrastructure.datasources

import com.movies.db.movies.domain.datasources.MoviesCacheDataSource
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.infrastructure.data.cache.MovieEntity
import com.movies.db.movies.infrastructure.mappers.MovieToEntityMapper
import com.movies.db.shared.domain.core.Resource
import com.movies.db.shared.infrastructure.config.util.Constants
import io.realm.Realm
import javax.inject.Inject

class MoviesCacheDataSourceImpl @Inject constructor(
    private val realm: Realm,
    private val movieMapper: MovieToEntityMapper,
) : MoviesCacheDataSource {
    override suspend fun getNowPlaying(): Resource<List<Movie>> {
        val data = realm.where(MovieEntity::class.java)
            .findAll()
        if (data != null) {
            return Resource.Success(movieMapper.mapMoviesDBEntitiesItemListToMovieList(data));
        }
        return Resource.Error(Constants.ERROR_GET_DATA)
    }
}