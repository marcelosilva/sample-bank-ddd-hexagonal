package com.marcelosilva.samples.ddd.bank.domain.exception

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId


class TransferException(id: AccountId, toAccount: AccountId, val businessCause: TransferExceptionErrorCause) :
    Exception("Error while transferring from account $id to account $toAccount: $businessCause")

enum class TransferExceptionErrorCause {
    INSUFFICIENT_FUNDS,
    INVALID_AMOUNT,
    INVALID_DESTINATION,
    LOCK_ACCOUNT_FAIL
}