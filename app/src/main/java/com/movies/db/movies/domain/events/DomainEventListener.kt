package com.movies.db.movies.domain.events

interface DomainEventListener {
    fun onEventOccurred(event: DomainEvent)
}