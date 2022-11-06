package com.marcelosilva.samples.ddd.bank.api.rest.v1.routes

import com.marcelosilva.samples.ddd.bank.api.rest.v1.dto.TransferRequest
import com.marcelosilva.samples.ddd.bank.api.rest.v1.dto.TransferResponse
import com.marcelosilva.samples.ddd.bank.application.command.TransferCommand
import com.marcelosilva.samples.ddd.common.application.command.CommandBus
import org.springframework.stereotype.Component

@Component
open class TransferEndpointImpl(
    private val commandBus: CommandBus<TransferCommand>
) : TransferEndpoint {

    override suspend fun transfer(id: String, transferRequest: TransferRequest): TransferResponse {
        commandBus.execute(
            TransferCommand(
                accountFromId = id,
                accountToId = transferRequest.destinationId,
                amount = transferRequest.amount
            )
        )
        return TransferResponse(message = "OK")
    }
}

