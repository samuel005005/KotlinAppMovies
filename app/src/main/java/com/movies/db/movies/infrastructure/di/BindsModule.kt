package com.movies.db.movies.infrastructure.di

import com.movies.db.movies.domain.datasources.MoviesCacheDataSource
import com.movies.db.movies.domain.datasources.MoviesRemoteDataSource
import com.movies.db.movies.domain.repository.MoviesDbRepository
import com.movies.db.movies.infrastructure.datasources.MoviesCacheDataSourceImpl
import com.movies.db.movies.infrastructure.datasources.MoviesRemoteDataSourceImpl
import com.movies.db.movies.infrastructure.repositories.MoviesDbRepositoryImpl
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
        moviesRemoteDataSource: MoviesRemoteDataSourceImpl
    ): MoviesRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindMoviesCacheDataSource(
        moviesDataSource: MoviesCacheDataSourceImpl
    ): MoviesCacheDataSource

    @Binds
    @Singleton
    abstract fun bindMoviesDbRepository(
        moviesDbRepository: MoviesDbRepositoryImpl
    ): MoviesDbRepository
}