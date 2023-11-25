package com.movies.db.movies.application

import com.movies.db.movies.application.usecases.GetNowPlayingUseCase
import com.movies.db.movies.application.usecases.GetPopularUseCase
import com.movies.db.movies.domain.repository.MoviesDbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetNowPlayingUseCase(
        moviesDbRepository: MoviesDbRepository,
    ): GetNowPlayingUseCase {
        return GetNowPlayingUseCase(
            moviesDbRepository
        )
    }
    @Provides
    @Singleton
    fun provideGetPopularUseCase(
        moviesDbRepository: MoviesDbRepository,
    ): GetPopularUseCase {
        return GetPopularUseCase(
            moviesDbRepository
        )
    }
}