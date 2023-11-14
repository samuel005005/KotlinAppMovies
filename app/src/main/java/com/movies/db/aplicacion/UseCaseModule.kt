package com.movies.db.aplicacion

import com.movies.db.aplicacion.usecases.GetNowPlayingUseCase
import com.movies.db.domain.repository.MoviesDbRepository
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
}