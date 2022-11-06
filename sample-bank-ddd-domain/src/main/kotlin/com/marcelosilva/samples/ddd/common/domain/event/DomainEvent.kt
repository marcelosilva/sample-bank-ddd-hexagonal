package com.marcelosilva.samples.ddd.common.domain.event

import java.io.Serializable
import java.time.LocalDateTime
import java.util.UUID

abstract class DomainEvent<D : DomainEventData> : Serializable {
    lateinit var eventData: D
}

abstract class DomainEventData(val id: UUID? = UUID.randomUUID(), val timestamp: LocalDateTime? = LocalDateTime.now())