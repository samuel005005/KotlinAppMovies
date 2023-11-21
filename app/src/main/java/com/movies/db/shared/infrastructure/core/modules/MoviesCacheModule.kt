package com.movies.db.shared.infrastructure.core.modules

import com.movies.db.movies.infrastructure.data.cache.MovieEntity
import io.realm.annotations.RealmModule

@RealmModule(library = true, classes = [MovieEntity::class])
class MoviesCacheModule