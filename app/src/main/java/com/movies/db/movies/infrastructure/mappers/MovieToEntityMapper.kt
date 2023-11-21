package com.movies.db.movies.infrastructure.mappers

import com.movies.db.shared.domain.core.Mapper
import com.movies.db.movies.domain.entities.Movie
import com.movies.db.movies.infrastructure.data.cache.MovieEntity
import com.movies.db.movies.infrastructure.data.remote.responses.MovieDbResponse
import com.movies.db.movies.infrastructure.data.remote.responses.MoviesDbResponse
import io.realm.RealmResults
import javax.inject.Inject

class MovieToEntityMapper @Inject constructor() :
    Mapper<MovieEntity, Movie>() {
    override fun map(value: MovieEntity): Movie {
        return Movie(
            adult = value.adult,
            backdropPath = "https://image.tmdb.org/t/p/w780/${value.backdropPath}",
            genreIds = value.genreIds!!,
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

    override fun reverseMap(value: Movie): MovieEntity {
        TODO("Not yet implemented")
    }

    fun mapMoviesDBEntitiesItemListToMovieList(
        moviesEntity: RealmResults<MovieEntity>
    ): ArrayList<Movie> {
        val movieList: ArrayList<Movie> = arrayListOf()
        moviesEntity.forEach {
            movieList.add(map(it))
        }
        return movieList
    }
}