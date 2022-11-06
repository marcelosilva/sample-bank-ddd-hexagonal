package com.marcelosilva.samples.ddd.bank.domain.exception

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId


class AccountWithdrawException(id: AccountId, cause: AccountWithdrawErrorCause) :
    Exception("Error while withdrawing from account: $id: $cause")

enum class AccountWithdrawErrorCause {
    INSUFFICIENT_FUNDS,
    LOCK_ACCOUNT_FAIL,
    INVALID_DATA
}