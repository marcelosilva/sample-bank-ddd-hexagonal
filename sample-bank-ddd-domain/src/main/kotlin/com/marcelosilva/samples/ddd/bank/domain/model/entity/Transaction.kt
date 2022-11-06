package com.marcelosilva.samples.ddd.bank.domain.model.entity

import java.math.BigDecimal
import java.util.UUID


data class Transaction(
    private val id: TransactionId,
    private val description: TransactionDescription,
    private val amount: TransactionAmount,
) {

    fun id(): TransactionId {
        return id
    }

    fun description(): TransactionDescription {
        return description
    }

    fun amount(): TransactionAmount {
        return amount
    }
}

data class TransactionId(
    val id: UUID
)

data class TransactionDescription(
    private var description: String
) {
    fun description(): String {
        return description
    }
}

data class TransactionAmount(
    private var amount: BigDecimal
) {
    fun amount(): BigDecimal {
        return amount
    }
}