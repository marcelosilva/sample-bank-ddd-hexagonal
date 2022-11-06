package com.marcelosilva.samples.ddd.bank.infrastructure.service

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.bank.domain.processor.LockAccountProcessor
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class LockAccountProcessorAdapter : LockAccountProcessor {
    private var log = LoggerFactory.getLogger(LockAccountProcessorAdapter::class.java)

    override suspend fun lock(accountId: AccountId): Boolean {
        log.info("Locked account ${accountId.id}")
        return true
    }

    override suspend fun unlock(accountId: AccountId): Boolean {
        log.info("Unlocked account ${accountId.id}")
        return false
    }
}