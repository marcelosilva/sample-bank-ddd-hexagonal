package com.marcelosilva.samples.ddd.bank.domain.port

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.Account
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId


interface AccountRepository {
    suspend fun findById(accountId: AccountId): Account?
    suspend fun edit(account: Account)
}
