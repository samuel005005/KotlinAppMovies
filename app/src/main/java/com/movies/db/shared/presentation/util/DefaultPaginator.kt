package com.movies.db.shared.presentation.util

import com.movies.db.shared.domain.core.Paginator
import com.movies.db.shared.domain.core.Resource

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Resource<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) : Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false

        when (result) {
            is Resource.Success -> {
                val items = result.data ?: emptyList()
                currentKey = getNextKey(items)
                onSuccess(items, currentKey)
                onLoadUpdated(false)
            }

            else -> {
                onError(Throwable(result.message))
                onLoadUpdated(false)
                return
            }
        }
    }

    override suspend fun reset() {
        currentKey = initialKey
    }

}