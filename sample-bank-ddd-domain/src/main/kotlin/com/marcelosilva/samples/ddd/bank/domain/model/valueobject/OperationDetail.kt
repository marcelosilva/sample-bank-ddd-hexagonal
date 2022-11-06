package com.marcelosilva.samples.ddd.bank.domain.model.valueobject

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId

class OperationDetail(
    val description: String
) {

    companion object {
        fun createForTransfer(accountId: AccountId) =
            OperationDetail(
                description = "Sent transfer for account ${accountId.id}"
            )

        fun createFromTransfer(accountId: AccountId) =
            OperationDetail(
                description = "Received transfer from account ${accountId.id}"
            )

        fun createWithDraw(accountId: AccountId) = OperationDetail(
            description = "Withdraw"
        )
    }

}