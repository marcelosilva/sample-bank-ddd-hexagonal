package com.marcelosilva.samples.ddd.bank.domain.event

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.common.domain.event.DomainEvent
import com.marcelosilva.samples.ddd.common.domain.event.DomainEventData
import java.math.BigDecimal

class WithdrawEvent(accountId: AccountId, amount: BigDecimal) : DomainEvent<WithdrawEventData>() {
    init {
        eventData = WithdrawEventData(accountId = accountId.id.toString(), amount = amount)
    }
}

data class WithdrawEventData(
    val accountId: String,
    val amount: BigDecimal
) : DomainEventData()