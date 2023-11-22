package com.movies.db.movies.infrastructure.repositories

import com.movies.db.shared.domain.core.Resource
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.domain.repository.MoviesDbRepository
import com.movies.db.movies.infrastructure.datasources.MoviesDataSourceFactory
import com.movies.db.movies.infrastructure.mappers.MovieToEntityMapper
import com.movies.db.movies.infrastructure.mappers.MovieToResponseMapper
import com.movies.db.shared.infrastructure.config.util.Constants
import javax.inject.Inject


class MoviesDbRepositoryImpl @Inject constructor(
    private val moviesDataSourceFactory: MoviesDataSourceFactory,
    private val movieToEntityMapper: MovieToEntityMapper,
    private val movieToResponseMapper: MovieToResponseMapper
) : MoviesDbRepository {
    override suspend fun getNowPlaying(page: Int, refresh: Boolean): Resource<List<Movie>> {
        val movies = moviesDataSourceFactory.getCacheData().getNowPlaying(page);
        return if (movies.isEmpty() || refresh) {
            val result = moviesDataSourceFactory.getRemoteData().getNowPlaying(page)
            if (result.isSuccessful && result.body() != null) {
                Resource.Success(result.body()!!.results.map { movieToResponseMapper.map(it) })
            } else {
                Resource.Error(Constants.ERROR_GET_DATA)
            }
        } else {
            Resource.Success(movies.map { movieToEntityMapper.reverseMap(it) })
        }
    }
}