package com.marcelosilva.samples.ddd.bank.domain.processor

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import java.math.BigDecimal

interface LockAccountProcessor {
    suspend fun lock(accountId: AccountId): Boolean
    suspend fun unlock(accountId: AccountId): Boolean
}