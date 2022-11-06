package com.marcelosilva.samples.ddd.bank.application.command

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.bank.domain.processor.LockAccountProcessor
import com.marcelosilva.samples.ddd.bank.domain.processor.WithdrawProcessor
import com.marcelosilva.samples.ddd.bank.domain.service.AccountService
import com.marcelosilva.samples.ddd.common.application.command.CommandBus
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class WithdrawCommandHandler(
    private val accountService: AccountService,
    private val withdrawProcessor: WithdrawProcessor,
    private val lockAccountProcessor: LockAccountProcessor
) : CommandBus<WithdrawCommand> {
    override suspend  fun execute(command: WithdrawCommand) {
        val account = accountService.findById(AccountId(UUID.fromString(command.accountId)))
        account.withdraw(withdrawProcessor, lockAccountProcessor, command.amount)
        accountService.persist(account)
    }
}
