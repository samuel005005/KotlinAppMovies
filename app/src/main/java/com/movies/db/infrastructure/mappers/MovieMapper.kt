package com.movies.db.infrastructure.mappers

import com.movies.db.domain.core.Mapper
import com.movies.db.domain.entities.Movie
import com.movies.db.infrastructure.data.remote.responses.MoviesDBResponseItem
import javax.inject.Inject

class MovieMapper @Inject constructor() :
    Mapper<MoviesDBResponseItem, Movie>() {
    override fun map(value: MoviesDBResponseItem): Movie {
        TODO("Not yet implemented")
    }

    override fun reverseMap(value: Movie): MoviesDBResponseItem {
        TODO("Not yet implemented")
    }

    fun mapMoviesDBResponseItemListToMovieList(moviesDBResponseItem: ArrayList<MoviesDBResponseItem>): ArrayList<Movie> {
        val movieList: ArrayList<Movie> = arrayListOf()
        moviesDBResponseItem.forEach {
            movieList.add(map(it))
        }
        return movieList
    }


}