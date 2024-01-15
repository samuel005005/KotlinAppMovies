package com.movies.db.movies.domain.events

interface DomainEvent {
    fun execute()
}

//data class DomainEvent(val eventType: String, val eventData: Any)