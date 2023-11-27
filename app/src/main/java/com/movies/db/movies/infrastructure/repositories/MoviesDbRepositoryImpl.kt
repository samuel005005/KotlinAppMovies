package com.movies.db.movies.infrastructure.repositories

import com.movies.db.movies.domain.datasources.MoviesCacheDataSource
import com.movies.db.app.core.Resource
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.domain.repository.MoviesDbRepository
import com.movies.db.movies.infrastructure.data.remote.responses.MoviesDbResponse
import com.movies.db.movies.infrastructure.datasources.MoviesDataSourceFactory
import com.movies.db.movies.infrastructure.mappers.MovieToEntityMapper
import com.movies.db.movies.infrastructure.mappers.MovieToResponseMapper
import com.movies.db.app.config.util.Constants
import retrofit2.Response
import javax.inject.Inject


class MoviesDbRepositoryImpl @Inject constructor(
    private val moviesDataSourceFactory: MoviesDataSourceFactory,
    private val movieToEntityMapper: MovieToEntityMapper,
    private val movieToResponseMapper: MovieToResponseMapper
) : MoviesDbRepository {
    override suspend fun getNowPlaying(page: Int, refresh: Boolean): Resource<List<Movie>> {
        val cache = moviesDataSourceFactory.getCacheData()
        val movies = cache.getNowPlaying(page);
        return if (movies.isEmpty() || refresh) {
            val result = moviesDataSourceFactory.getRemoteData().getNowPlaying(page)
            validateResponse(result, cache)
        } else {
            Resource.Success(movies.map { movieToEntityMapper.reverseMap(it) })
        }
    }

    override suspend fun getPopular(page: Int, refresh: Boolean): Resource<List<Movie>> {
        val cache = moviesDataSourceFactory.getCacheData()
        val movies = cache.getPopular(page);
        return if (movies.isEmpty() || refresh) {
            val result = moviesDataSourceFactory.getRemoteData().getPopular(page)
            validateResponse(result, cache)
        } else {
            Resource.Success(movies.map { movieToEntityMapper.reverseMap(it) })
        }
    }

    override suspend fun getTopRated(page: Int, refresh: Boolean): Resource<List<Movie>> {
        val cache = moviesDataSourceFactory.getCacheData()
        val movies = cache.getTopRated(page);
        return if (movies.isEmpty() || refresh) {
            val result = moviesDataSourceFactory.getRemoteData().getTopRated(page)
            validateResponse(result, cache)
        } else {
            Resource.Success(movies.map { movieToEntityMapper.reverseMap(it) })
        }
    }

    override suspend fun getUpcoming(page: Int, refresh: Boolean): Resource<List<Movie>> {
        val cache = moviesDataSourceFactory.getCacheData()
        val movies = cache.getUpcoming(page);
        return if (movies.isEmpty() || refresh) {
            val result = moviesDataSourceFactory.getRemoteData().getUpcoming(page)
            validateResponse(result, cache)
        } else {
            Resource.Success(movies.map { movieToEntityMapper.reverseMap(it) })
        }
    }

    private suspend fun validateResponse(
        result: Response<MoviesDbResponse>, cache: MoviesCacheDataSource
    ): Resource<List<Movie>> {
        return if (result.isSuccessful && result.body() != null) {
            val movies = result.body()!!
            cache.saveMovies(movies.results.map {
                movieToResponseMapper.map(it)
            }.map {
                movieToEntityMapper.map(it)
            }.filter { cache.validateExistMovie(it) })
            Resource.Success(movies.results.map { movieToResponseMapper.map(it) })
        } else {
            Resource.Error(Constants.ERROR_GET_DATA)
        }
    }
}