package com.marcelosilva.samples.ddd.bank.application.command

import com.marcelosilva.samples.ddd.common.application.command.Command
import java.math.BigDecimal

data class TransferCommand(
    val accountFromId: String,
    val accountToId: String,
    val amount: BigDecimal
) : Command