package com.marcelosilva.samples.ddd.bank.domain.service

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.Account
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.bank.domain.port.AccountRepository
import com.marcelosilva.samples.ddd.common.domain.event.DomainEventDispatcher
import com.marcelosilva.samples.ddd.common.domain.service.DomainService

@DomainService
class AccountService(
    private val accountRepository: AccountRepository,
    private val domainEventDispatcher: DomainEventDispatcher
) {
    suspend fun persist(account: Account) {
        accountRepository.edit(account)
        domainEventDispatcher.pullAndPropagate(account)
    }

    suspend fun findById(accountId: AccountId): Account {
        return accountRepository.findById(accountId)
    }
}
