package com.marcelosilva.samples.ddd.bank.application.event.listener

import com.marcelosilva.samples.ddd.bank.domain.event.CreditEvent
import com.marcelosilva.samples.ddd.bank.domain.event.DebitEvent
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.Transaction
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.TransactionAmount
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.TransactionDescription
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.TransactionId
import com.marcelosilva.samples.ddd.bank.domain.service.TransactionService
import com.marcelosilva.samples.ddd.common.domain.service.DomainService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.springframework.context.event.EventListener
import java.util.UUID

@DomainService
open class TransactionListener(
    private val applicationScope: CoroutineScope,
    private val transactionService: TransactionService
) {

    @EventListener
    fun handleDebitEvent(domainEvent: DebitEvent) {
        applicationScope.launch {
            val eventData = domainEvent.eventData
            val transaction = Transaction(
                id = TransactionId(),
                description = TransactionDescription(eventData.detail ?: ""),
                accountId = AccountId(UUID.fromString(eventData.accountId)),
                amount = TransactionAmount(amount = eventData.amount.negate())
            )
            transactionService.persist(transaction)
        }
    }

    @EventListener
    fun handleCreditEvent(domainEvent: CreditEvent) {
        applicationScope.launch {
            val eventData = domainEvent.eventData
            val transaction = Transaction(
                id = TransactionId(),
                description = TransactionDescription(eventData.detail ?: ""),
                accountId = AccountId(UUID.fromString(eventData.accountId)),
                amount = TransactionAmount(amount = eventData.amount)
            )
            transactionService.persist(transaction)
        }
    }
}
