package com.marcelosilva.samples.ddd.bank.domain.event

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.common.domain.event.DomainEvent
import com.marcelosilva.samples.ddd.common.domain.event.DomainEventData
import java.math.BigDecimal

class TransferEvent(fromAccountId: AccountId, toAccountId: AccountId, amount: BigDecimal) :
    DomainEvent<TransferEventData>() {
    init {
        eventData = TransferEventData(
            fromAccountId = fromAccountId.id.toString(),
            toAccountId = toAccountId.id.toString(),
            amount = amount
        )
    }
}

data class TransferEventData(
    val fromAccountId: String, val toAccountId: String, val amount: BigDecimal
) : DomainEventData()