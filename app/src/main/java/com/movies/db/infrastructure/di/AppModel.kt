package com.pokemon.moviesapp.infrastructure.di

import com.movies.db.infrastructure.config.util.Constants.THE_MOVIEDB_URL
import com.pokemon.moviesapp.infrastructure.data.remote.MoviesDbApi
import com.pokemon.moviesapp.infrastructure.datasources.MoviesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object   AppModel {
    @Singleton
    @Provides
    fun provideMovieDbDataSource(
        api: MoviesDbApi
    ) = MoviesDataSource(api)

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