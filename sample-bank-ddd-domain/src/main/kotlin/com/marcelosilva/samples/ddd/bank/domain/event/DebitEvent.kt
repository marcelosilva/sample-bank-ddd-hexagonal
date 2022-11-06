package com.marcelosilva.samples.ddd.bank.domain.event

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.common.domain.event.DomainEvent
import com.marcelosilva.samples.ddd.common.domain.event.DomainEventData
import java.math.BigDecimal

class DebitEvent(accountId: AccountId, amount: BigDecimal,  detail: String? = null) :
    DomainEvent<DebitEventData>() {
    init {
        eventData = DebitEventData(accountId = accountId.id.toString(), amount = amount, detail = detail)
    }
}

data class DebitEventData(
    val accountId: String,
    val amount: BigDecimal,
    val detail: String?
) : DomainEventData()