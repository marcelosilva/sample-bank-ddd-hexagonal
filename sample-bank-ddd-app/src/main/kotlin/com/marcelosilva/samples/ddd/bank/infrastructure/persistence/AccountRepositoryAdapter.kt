package com.marcelosilva.samples.ddd.bank.infrastructure.persistence

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.Account
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountBalance
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.bank.domain.port.AccountRepository
import com.marcelosilva.samples.ddd.bank.infrastructure.persistence.jpa.AccountJpaRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class AccountRepositoryAdapter(private val accountJpaRepository: AccountJpaRepository) : AccountRepository {

    private var log = LoggerFactory.getLogger(AccountRepositoryAdapter::class.java)

    override suspend fun findById(accountId: AccountId): Account? {
        return accountJpaRepository.findById(accountId.id)?.let { account ->
            Account(accountId, AccountBalance(account.balance!!))
        }
    }

    override suspend fun edit(account: Account) {
        accountJpaRepository.findById(account.id().id)?.run {
            balance = account.balance().funds()
            accountJpaRepository.save(this)
        }.also {
            log.info("edited account with id ${account.id().id}")
        }

    }

}
