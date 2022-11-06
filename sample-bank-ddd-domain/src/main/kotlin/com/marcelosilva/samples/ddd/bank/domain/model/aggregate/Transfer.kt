package com.marcelosilva.samples.ddd.bank.domain.model.aggregate

import com.marcelosilva.samples.ddd.bank.domain.event.TransferEvent
import com.marcelosilva.samples.ddd.bank.domain.exception.TransferException
import com.marcelosilva.samples.ddd.bank.domain.exception.TransferExceptionErrorCause
import com.marcelosilva.samples.ddd.bank.domain.model.valueobject.OperationDetail
import com.marcelosilva.samples.ddd.bank.domain.processor.LockAccountProcessor
import com.marcelosilva.samples.ddd.common.domain.AggregateRoot
import java.math.BigDecimal
import java.util.UUID

class Transfer(
    private val id: TransferId,
    private val fromAccount: Account,
    private val toAccount: Account,
    private val amount: BigDecimal
) : AggregateRoot() {

    init {
        validate()
    }

    fun id(): TransferId {
        return id
    }

    suspend fun execute(lockAccountProcessor: LockAccountProcessor) {
        fromAccount.debit(lockAccountProcessor, amount, OperationDetail.createForTransfer(toAccount.id()))
        toAccount.credit(amount, OperationDetail.createFromTransfer(fromAccount.id()))
        recordEvent(
            TransferEvent(
                fromAccount.id(), toAccount.id(), amount
            )
        )
    }

    private fun validate() {
        if (fromAccount.id() == toAccount.id()) {
            throw TransferException(fromAccount.id(), toAccount.id(), TransferExceptionErrorCause.INVALID_DESTINATION)
        }
        if (amount <= BigDecimal.ZERO) {
            throw TransferException(fromAccount.id(), toAccount.id(), TransferExceptionErrorCause.INVALID_AMOUNT)
        }
    }
}

data class TransferId(
    val id: UUID? = UUID.randomUUID()
)

