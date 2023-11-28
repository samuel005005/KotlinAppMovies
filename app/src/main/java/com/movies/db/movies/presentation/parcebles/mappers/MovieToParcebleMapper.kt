package com.movies.db.movies.presentation.parcebles.mappers

import com.movies.db.app.core.Mapper
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.infrastructure.data.cache.MovieEntity
import com.movies.db.movies.presentation.parcebles.entities.MovieParcelize
import javax.inject.Inject

class MovieToParcebleMapper @Inject constructor() : Mapper<Movie, MovieParcelize>() {
    override fun map(value: Movie): MovieParcelize {
        return MovieParcelize(
            adult = value.adult,
            backdropPath = value.backdropPath,
            genreIds = value.genreIds,
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

    override fun reverseMap(value: MovieParcelize): Movie {
        return Movie(
            adult = value.adult,
            backdropPath = value.backdropPath,
            genreIds = value.genreIds,
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
}