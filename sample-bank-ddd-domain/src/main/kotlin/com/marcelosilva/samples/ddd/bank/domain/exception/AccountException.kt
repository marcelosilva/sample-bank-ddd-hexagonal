package com.marcelosilva.samples.ddd.bank.domain.exception

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId


class AccountException(id: AccountId, val businessCause: AccountExceptionErrorCause) :
    Exception("Error with  account $id: $businessCause")

enum class AccountExceptionErrorCause {
    NOT_FOUND
}