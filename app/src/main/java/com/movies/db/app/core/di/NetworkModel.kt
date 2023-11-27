package com.movies.db.app.di

import com.movies.db.app.config.util.Constants.THE_MOVIEDB_URL
import com.movies.db.movies.infrastructure.data.remote.MoviesDbApi
import com.movies.db.app.core.data.RetrofitServiceBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModel {
    @Singleton
    @Provides
    fun provideMovieDbApi(): MoviesDbApi {
        return RetrofitServiceBuilder(THE_MOVIEDB_URL).buildService(MoviesDbApi::class.java)
    }

}