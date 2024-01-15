package com.movies.db.movies.domain.events

object EventManager {
    private val listeners = mutableListOf<DomainEventListener>()
    fun addListener(listener: DomainEventListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: DomainEventListener) {
        listeners.remove(listener)
    }

    fun postEvent(event: DomainEvent) {
        for (listener in listeners) {
            listener.onEventOccurred(event)
        }
    }
}