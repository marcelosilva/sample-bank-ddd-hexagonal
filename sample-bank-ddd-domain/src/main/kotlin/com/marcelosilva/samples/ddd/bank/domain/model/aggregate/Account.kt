package com.marcelosilva.samples.ddd.bank.domain.model.aggregate

import com.marcelosilva.samples.ddd.bank.domain.event.CreditEvent
import com.marcelosilva.samples.ddd.bank.domain.event.DebitEvent
import com.marcelosilva.samples.ddd.bank.domain.event.WithdrawEvent
import com.marcelosilva.samples.ddd.bank.domain.exception.AccountWithdrawErrorCause
import com.marcelosilva.samples.ddd.bank.domain.exception.AccountWithdrawException
import com.marcelosilva.samples.ddd.bank.domain.model.valueobject.OperationDetail
import com.marcelosilva.samples.ddd.bank.domain.processor.LockAccountProcessor
import com.marcelosilva.samples.ddd.bank.domain.processor.WithdrawProcessor
import com.marcelosilva.samples.ddd.common.domain.AggregateRoot
import java.math.BigDecimal
import java.util.UUID

class Account(
    private val id: AccountId,
    private val balance: AccountBalance,
) : AggregateRoot() {

    fun id(): AccountId {
        return id
    }

    fun balance(): AccountBalance {
        return balance
    }

    suspend fun withdraw(
        withDrawProcessor: WithdrawProcessor,
        lockAccountProcessor: LockAccountProcessor,
        amount: BigDecimal
    ) {
        debit(lockAccountProcessor, amount, OperationDetail.createWithDraw(id))
        withDrawProcessor.withdraw(id, amount)
        recordEvent(WithdrawEvent(id, amount))
    }

    fun credit(amount: BigDecimal, detail: OperationDetail? = null) {
        validateAmount(amount)
        balance.add(amount)
        recordEvent(CreditEvent(id, amount, detail?.description))
    }

    suspend fun debit(
        lockAccountProcessor: LockAccountProcessor,
        amount: BigDecimal,
        detail: OperationDetail? = null
    ) {
        validateAmount(amount)
        if (lockAccountProcessor.lock(id)) {
            if (!balance.hasSufficientFundsComparingAmount(amount)) {
                throw AccountWithdrawException(id, AccountWithdrawErrorCause.INSUFFICIENT_FUNDS)
            }
            balance.subtract(amount)
            recordEvent(DebitEvent(id, amount, detail?.description))
            lockAccountProcessor.unlock(id)
        } else {
            throw AccountWithdrawException(id, AccountWithdrawErrorCause.LOCK_ACCOUNT_FAIL)
        }
    }

    private fun validateAmount(amount: BigDecimal) {
        if (amount <= BigDecimal.ZERO) {
            throw AccountWithdrawException(id, AccountWithdrawErrorCause.INVALID_DATA)
        }
    }
}

data class AccountId(
    val id: UUID
)

data class AccountBalance(
    private var funds: BigDecimal
) {

    fun funds() : BigDecimal {
        return funds
    }

    fun hasSufficientFundsComparingAmount(amountToCompare: BigDecimal): Boolean {
        return funds >= amountToCompare
    }

    fun subtract(amount: BigDecimal) {
        funds = funds.subtract(amount)
    }

    fun add(amount: BigDecimal) {
        funds = funds.add(amount)
    }
}


