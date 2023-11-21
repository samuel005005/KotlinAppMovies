package com.movies.db.movies.infrastructure.mappers

import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.infrastructure.data.remote.responses.MovieDbResponse
import com.movies.db.movies.infrastructure.data.remote.responses.MoviesDbResponse
import com.movies.db.shared.domain.core.Mapper
import javax.inject.Inject

class MovieToResponseMapper @Inject constructor() : Mapper<MovieDbResponse, Movie>() {

    override fun map(value: MovieDbResponse): Movie {
        return Movie(
            adult = value.adult,
            backdropPath = "https://image.tmdb.org/t/p/w780/${value.backdropPath}",
            genreIds = value.genreIDS,
            id = value.id,
            originalLanguage = value.originalLanguage,
            originalTitle = value.originalTitle,
            overview = value.overview,
            popularity = value.popularity,
            posterPath = "https://image.tmdb.org/t/p/w500/${value.posterPath}",
            releaseDate = value.releaseDate,
            title = value.title,
            video = value.video,
            voteAverage = value.voteAverage,
            voteCount = value.voteCount,
        )
    }

    override fun reverseMap(value: Movie): MovieDbResponse {
        return MovieDbResponse(
            adult = value.adult,
            backdropPath = "https://image.tmdb.org/t/p/w780/${value.backdropPath}",
            genreIDS = value.genreIds,
            id = value.id,
            originalLanguage = value.originalLanguage,
            originalTitle = value.originalTitle,
            overview = value.overview,
            popularity = value.popularity,
            posterPath = "https://image.tmdb.org/t/p/w500/${value.posterPath}",
            releaseDate = value.releaseDate,
            title = value.title,
            video = value.video,
            voteAverage = value.voteAverage,
            voteCount = value.voteCount,
        )
    }

    fun mapMoviesDBResponseItemListToMovieList(moviesDbResponse: MoviesDbResponse): ArrayList<Movie> {
        val movieList: ArrayList<Movie> = arrayListOf()
        moviesDbResponse.results.forEach {
            movieList.add(map(it))
        }
        return movieList
    }
}