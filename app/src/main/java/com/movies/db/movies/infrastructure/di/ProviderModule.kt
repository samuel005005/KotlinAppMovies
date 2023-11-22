package com.movies.db.movies.infrastructure.di

import com.movies.db.movies.infrastructure.datasources.MoviesDataSourceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {
    @Provides
    @Singleton
    fun provideMoviesDataSourceFactory(
        moviesRemoteDataSource: MoviesRemoteDataSource,
        moviesCacheDataSource: MoviesCacheDataSource
    ): MoviesDataSourceFactory {
        return MoviesDataSourceFactory(
            moviesRemoteDataSource,
            moviesCacheDataSource
        )
    }
}