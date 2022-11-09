package com.marcelosilva.samples.ddd.bank.infrastructure.persistence.jpa

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.UUID

interface TransactionJpaRepository : CoroutineCrudRepository<TransactionEntity, UUID>
