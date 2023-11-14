package com.movies.db.infrastructure.di

import com.movies.db.aplicacion.usecases.GetNowPlayingUseCase
import com.movies.db.domain.datasources.MoviesDataSource
import com.movies.db.domain.repository.MoviesDbRepository
import com.movies.db.infrastructure.datasources.MoviesDataSourceImpl
import com.movies.db.infrastructure.repositories.MoviesDbRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun binMoviesDbRepository(
        moviesDbRepository: MoviesDbRepositoryImpl
    ): MoviesDbRepository
}