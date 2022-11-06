package com.marcelosilva.samples.ddd.bank.domain.event

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.common.domain.event.DomainEvent
import com.marcelosilva.samples.ddd.common.domain.event.DomainEventData
import java.math.BigDecimal

class CreditEvent(accountId: AccountId, amount: BigDecimal, detail: String?) :
    DomainEvent<CreditEventData>() {
    init {
        eventData =
            CreditEventData(accountId = accountId.id.toString(), amount = amount, detail = detail)
    }
}

data class CreditEventData(
    val accountId: String,
    val amount: BigDecimal,
    val detail: String?
) : DomainEventData()