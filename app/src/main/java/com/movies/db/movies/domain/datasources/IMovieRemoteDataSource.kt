package com.movies.db.movies.domain.datasources

import com.movies.db.movies.infrastructure.data.remote.responses.MoviesDbResponse
import retrofit2.Response

interface IMovieRemoteDataSource : IMoviesData<Response<MoviesDbResponse>>