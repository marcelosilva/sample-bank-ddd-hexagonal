package com.marcelosilva.samples.ddd.bank.api.rest.v1.routes

import com.marcelosilva.samples.ddd.bank.api.rest.v1.dto.WithdrawRequest
import com.marcelosilva.samples.ddd.bank.api.rest.v1.dto.WithdrawResponse
import com.marcelosilva.samples.ddd.bank.application.command.WithdrawCommand
import com.marcelosilva.samples.ddd.common.application.command.CommandBus
import org.springframework.stereotype.Component

@Component
open class WithdrawEndpointImpl(
    private val commandBus: CommandBus<WithdrawCommand>
) : WithdrawEndpoint {

    override suspend fun withdraw(id: String, withdrawRequest: WithdrawRequest): WithdrawResponse {
        commandBus.execute(
            WithdrawCommand(
                accountId = id,
                amount = withdrawRequest.amount
            )
        )
        return WithdrawResponse(message = "OK")
    }
}

