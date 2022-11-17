package com.marcelosilva.samples.ddd.bank.infrastructure.persistence

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.Transaction
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.TransactionAmount
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.TransactionDescription
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.TransactionId
import com.marcelosilva.samples.ddd.bank.domain.port.TransactionRepository
import com.marcelosilva.samples.ddd.bank.infrastructure.persistence.jpa.TransactionEntity
import com.marcelosilva.samples.ddd.bank.infrastructure.persistence.jpa.TransactionJpaRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class TransactionRepositoryAdapter(
    private val transactionJpaRepository: TransactionJpaRepository
) : TransactionRepository {

    private var log = LoggerFactory.getLogger(TransactionRepositoryAdapter::class.java)

    override suspend fun save(transaction: Transaction) {
        transactionJpaRepository.save(
            TransactionEntity(
                transactionId = transaction.id().id,
                amount = transaction.amount().amount,
                accountId = transaction.accountId().id,
                description = transaction.description().description
            )
        )
    }

    override suspend fun findByAccountId(accountId: AccountId): List<Transaction> {
        val transactions = transactionJpaRepository.findByAccountId(accountId.id)
        return transactions.map {
            Transaction(
                id = TransactionId(it.transactionId!!),
                accountId = AccountId(it.accountId!!),
                description = TransactionDescription(it.description!!),
                amount = TransactionAmount(it.amount!!)
            )
        }
    }
}
