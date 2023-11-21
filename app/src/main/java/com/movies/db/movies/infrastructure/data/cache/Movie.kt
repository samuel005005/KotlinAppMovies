package com.movies.db.movies.infrastructure.data.cache

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId


open class MovieEntity(
    @PrimaryKey
    var _id: ObjectId = ObjectId(),
    var adult: Boolean = false,
    var backdropPath: String = "",
    var genreIds: RealmList<Long>? = null,
    var id: Long = 0,
    var originalLanguage: String = "",
    var originalTitle: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var posterPath: String = "",
    var releaseDate: String = "",
    var title: String = "",
    var video: Boolean = false,
    var voteAverage: Double = 0.0,
    var voteCount: Long = 0
) : RealmObject()