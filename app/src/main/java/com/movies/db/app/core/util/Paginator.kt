package com.movies.db.app.core.util

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    suspend fun reset()
}