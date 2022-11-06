package com.marcelosilva.samples.ddd.bank.infrastructure.service

import com.marcelosilva.samples.ddd.common.domain.AggregateRoot
import com.marcelosilva.samples.ddd.common.domain.event.DomainEventDispatcher
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class DomainEventAdapter(
    private val applicationEventPublisher: ApplicationEventPublisher
) : DomainEventDispatcher {

    private val logger = LoggerFactory.getLogger(DomainEventAdapter::class.java)

    override fun pullAndPropagate(root: AggregateRoot) {
        for (event in root.pullEvents()) {
            logger.info("Logging event with data ${event.eventData}")
            applicationEventPublisher.publishEvent(event)
        }
    }
}