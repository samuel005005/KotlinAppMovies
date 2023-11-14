package com.movies.db.infrastructure.di

import com.movies.db.infrastructure.config.util.Constants.THE_MOVIEDB_URL
import com.movies.db.infrastructure.data.remote.MoviesDbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModel {
    @Singleton
    @Provides
    fun provideMovieDbApi(): MoviesDbApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(THE_MOVIEDB_URL)
            .build()
            .create(MoviesDbApi::class.java);
    }

}