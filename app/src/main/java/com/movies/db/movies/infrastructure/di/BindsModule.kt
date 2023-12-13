package com.movies.db.movies.infrastructure.di

import com.movies.db.movies.domain.datasources.IMovieRemoteDataSource
import com.movies.db.movies.domain.datasources.IMoviesCacheDataSource
import com.movies.db.movies.domain.datasources.IMoviesDataSource
import com.movies.db.movies.domain.repository.IMoviesDbRepository
import com.movies.db.movies.infrastructure.datasources.MoviesCacheDataSource
import com.movies.db.movies.infrastructure.datasources.MoviesDataSource
import com.movies.db.movies.infrastructure.datasources.MoviesRemoteDataSource
import com.movies.db.movies.infrastructure.repositories.MoviesDbRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {
    @Binds
    @Singleton
    abstract fun bindMoviesRemoteDataSource(
        moviesRemoteDataSource: MoviesRemoteDataSource
    ): IMovieRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindMoviesCacheDataSource(
        moviesDataSource: MoviesCacheDataSource
    ): IMoviesCacheDataSource

    @Binds
    @Singleton
    abstract fun bindMoviesDbRepository(
        moviesDbRepository: MoviesDbRepository
    ): IMoviesDbRepository
    @Binds
    @Singleton
    abstract fun bindMoviesDataSource(
        moviesDataSource: MoviesDataSource
    ): IMoviesDataSource

}