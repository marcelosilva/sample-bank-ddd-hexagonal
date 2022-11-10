package com.marcelosilva.samples.ddd.bank.domain.service

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.Transaction
import com.marcelosilva.samples.ddd.bank.domain.port.TransactionRepository
import com.marcelosilva.samples.ddd.common.domain.service.DomainService

@DomainService
class TransactionService(
    private val transactionRepository: TransactionRepository
) {
    suspend fun persist(transaction: Transaction) {
        transactionRepository.save(transaction)
    }

    suspend fun findByAccountId(id: AccountId): List<Transaction>  {
        return transactionRepository.findByAccountId(id)
    }
}
