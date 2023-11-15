package com.movies.db.infrastructure.di

import com.movies.db.infrastructure.config.util.Constants.THE_MOVIEDB_URL
import com.movies.db.infrastructure.data.remote.MoviesDbApi
import com.movies.db.infrastructure.data.remote.RetrofitServiceBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
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