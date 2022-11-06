package com.marcelosilva.samples.ddd.bank.domain.service

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.Transfer
import com.marcelosilva.samples.ddd.common.domain.event.DomainEventDispatcher
import com.marcelosilva.samples.ddd.common.domain.service.DomainService

@DomainService
class TransferService(
    private val domainEventDispatcher: DomainEventDispatcher
) {
    fun persist(transfer: Transfer) {
        domainEventDispatcher.pullAndPropagate(transfer)
    }
}
