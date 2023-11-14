package com.movies.db.infrastructure.di

import com.movies.db.domain.datasources.MoviesDataSource
import com.movies.db.domain.repository.MoviesDbRepository
import com.movies.db.infrastructure.repositories.MoviesDbRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProviderModule {
    @Singleton
    @Provides
    fun provideMoviesDbRepository(
        moviesDataSource: MoviesDataSource
    ): MoviesDbRepository {
        return MoviesDbRepositoryImpl(
            moviesDataSource
        )
    }
}