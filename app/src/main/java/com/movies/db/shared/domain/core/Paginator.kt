package com.movies.db.shared.domain.core

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    suspend fun reset()
}