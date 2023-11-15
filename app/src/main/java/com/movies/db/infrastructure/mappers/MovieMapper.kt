package com.movies.db.infrastructure.mappers

import com.movies.db.domain.core.Mapper
import com.movies.db.domain.entities.Movie
import com.movies.db.infrastructure.data.remote.responses.MovieDbResponse
import com.movies.db.infrastructure.data.remote.responses.MoviesDbResponse
import javax.inject.Inject

class MovieMapper @Inject constructor() :
    Mapper<MovieDbResponse, Movie>() {
    override fun map(value: MovieDbResponse): Movie {
        return Movie(
            adult = value.adult,
            backdropPath = value.backdropPath,
            genreIds = value.genreIDS,
            id = value.id,
            originalLanguage = value.originalLanguage,
            originalTitle = value.originalTitle,
            overview = value.overview,
            popularity = value.popularity,
            posterPath = value.posterPath,
            releaseDate = value.releaseDate,
            title = value.title,
            video = value.video,
            voteAverage = value.voteAverage,
            voteCount = value.voteCount,
        )
    }

    override fun reverseMap(value: Movie): MovieDbResponse {
        TODO("Not yet implemented")
    }

    fun mapMoviesDBResponseItemListToMovieList(moviesDbResponse: MoviesDbResponse): ArrayList<Movie> {
        val movieList: ArrayList<Movie> = arrayListOf()
        moviesDbResponse.results.forEach {
            movieList.add(map(it))
        }
        return movieList
    }


}