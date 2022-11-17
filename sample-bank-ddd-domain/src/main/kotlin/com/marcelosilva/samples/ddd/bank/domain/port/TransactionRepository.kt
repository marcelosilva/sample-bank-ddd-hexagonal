package com.marcelosilva.samples.ddd.bank.domain.port

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.Transaction


interface TransactionRepository {
    suspend fun save(transaction: Transaction)
    suspend fun findByAccountId(accountId: AccountId): List<Transaction>
}
