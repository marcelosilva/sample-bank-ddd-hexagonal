package com.marcelosilva.samples.ddd.common.domain.event

import com.marcelosilva.samples.ddd.common.domain.AggregateRoot

interface DomainEventDispatcher {
    fun pullAndPropagate(root: AggregateRoot)
}