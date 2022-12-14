package com.marcelosilva.samples.ddd.bank.domain.model.aggregate

import java.math.BigDecimal
import java.util.UUID


data class Transaction(
    private val id: TransactionId,
    private val accountId: AccountId,
    private val description: TransactionDescription,
    private val amount: TransactionAmount,
) {

    fun id(): TransactionId {
        return id
    }

    fun accountId(): AccountId {
        return accountId
    }

    fun description(): TransactionDescription {
        return description
    }

    fun amount(): TransactionAmount {
        return amount
    }
}

data class TransactionId(
    val id: UUID = UUID.randomUUID()
)

data class TransactionDescription(
    var description: String
)

data class TransactionAmount(
    var amount: BigDecimal
)