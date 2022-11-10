package com.marcelosilva.samples.ddd.bank.application.command

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.Transfer
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.TransferId
import com.marcelosilva.samples.ddd.bank.domain.processor.LockAccountProcessor
import com.marcelosilva.samples.ddd.bank.domain.service.AccountService
import com.marcelosilva.samples.ddd.bank.domain.service.TransferService
import com.marcelosilva.samples.ddd.common.application.command.CommandBus
import com.marcelosilva.samples.ddd.common.domain.service.DomainService
import java.util.UUID

@DomainService
class TransferCommandHandler(
    private val accountService: AccountService,
    private val transferService: TransferService,
    private val lockAccountProcessor: LockAccountProcessor
) : CommandBus<TransferCommand> {
    override suspend fun execute(command: TransferCommand) {
        val accountFrom = accountService.findById(AccountId(UUID.fromString(command.accountFromId)))
        val accountTo = accountService.findById(AccountId(UUID.fromString(command.accountToId)))

        val transfer = Transfer(TransferId(), accountFrom, accountTo, command.amount)
        transfer.execute(lockAccountProcessor)

        transferService.persist(transfer)
        accountService.persist(accountFrom)
        accountService.persist(accountTo)
    }
}
