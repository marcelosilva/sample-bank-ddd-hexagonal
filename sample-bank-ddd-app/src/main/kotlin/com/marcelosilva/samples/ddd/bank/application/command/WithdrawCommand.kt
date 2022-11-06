package com.marcelosilva.samples.ddd.bank.application.command

import com.marcelosilva.samples.ddd.common.application.command.Command
import java.math.BigDecimal

data class WithdrawCommand(
    val accountId: String,
    val amount: BigDecimal
) : Command