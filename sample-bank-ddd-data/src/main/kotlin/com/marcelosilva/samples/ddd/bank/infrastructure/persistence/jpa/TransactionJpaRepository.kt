package com.marcelosilva.samples.ddd.bank.infrastructure.persistence.jpa

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TransactionJpaRepository : CoroutineCrudRepository<TransactionEntity, UUID> {
    suspend fun findByAccountId(accountId: UUID): List<TransactionEntity>
}