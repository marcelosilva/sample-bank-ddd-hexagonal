package com.marcelosilva.samples.ddd.common.domain

import com.marcelosilva.samples.ddd.common.domain.event.DomainEvent

abstract class AggregateRoot {
    private var domainEvents = mutableListOf<DomainEvent<*>>()
    protected fun recordEvent(domainEvent: DomainEvent<*>) {
        domainEvents.add(domainEvent)
    }

    fun pullEvents(): List<DomainEvent<*>> {
        val pulledDomainEvents = mutableListOf<DomainEvent<*>>()
        pulledDomainEvents.addAll(domainEvents)
        domainEvents.clear()
        return pulledDomainEvents
    }
}