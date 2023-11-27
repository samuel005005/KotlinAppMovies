package com.movies.db.movies.presentation.parcelables.mappers

import com.movies.db.app.core.Mapper
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.infrastructure.data.cache.MovieEntity
import com.movies.db.movies.presentation.parcelables.entities.MovieDataView
import io.realm.RealmList
import javax.inject.Inject

class MovieToViewMovieMapper @Inject constructor() : Mapper<Movie, MovieDataView>() {
    override fun map(value: Movie): MovieDataView {
        return MovieDataView(
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

    override fun reverseMap(value: MovieDataView): Movie {
        return Movie(
            adult = value.adult,
            backdropPath = value.backdropPath,
            genreIds = value.genreIds?.map { it.toLong() } ?: emptyList(),
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