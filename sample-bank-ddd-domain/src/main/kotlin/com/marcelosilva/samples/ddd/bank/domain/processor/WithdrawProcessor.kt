package com.marcelosilva.samples.ddd.bank.domain.processor

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import java.math.BigDecimal

interface WithdrawProcessor {
    fun withdraw(accountId: AccountId, amount: BigDecimal)
}