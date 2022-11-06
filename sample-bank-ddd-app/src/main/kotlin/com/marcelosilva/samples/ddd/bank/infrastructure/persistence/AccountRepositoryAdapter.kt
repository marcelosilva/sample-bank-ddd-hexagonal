package com.marcelosilva.samples.ddd.bank.infrastructure.persistence

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.Account
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountBalance
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.bank.domain.port.AccountRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class AccountRepositoryAdapter : AccountRepository {

    private var log = LoggerFactory.getLogger(AccountRepositoryAdapter::class.java)

    override suspend fun findById(accountId: AccountId): Account {
        return Account(
            id = accountId,
            balance = AccountBalance(BigDecimal("100000.00"))
        )
    }

    override suspend fun edit(account: Account) {
        log.info("edited account with id ${account.id().id}")
    }

}
