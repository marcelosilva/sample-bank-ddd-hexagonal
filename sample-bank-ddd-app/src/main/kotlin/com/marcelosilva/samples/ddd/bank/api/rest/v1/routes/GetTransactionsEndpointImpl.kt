package com.marcelosilva.samples.ddd.bank.api.rest.v1.routes

import com.marcelosilva.samples.ddd.bank.api.rest.v1.dto.GetTransactionsResponse
import com.marcelosilva.samples.ddd.bank.api.rest.v1.dto.TransactionResponse
import com.marcelosilva.samples.ddd.bank.application.query.GetTransactionsQuery
import com.marcelosilva.samples.ddd.bank.application.query.GetTransactionsQueryResponse
import com.marcelosilva.samples.ddd.common.application.query.QueryHandler
import org.springframework.stereotype.Component

@Component
open class GetTransactionsEndpointImpl(
    private val queryBus: QueryHandler<GetTransactionsQuery, GetTransactionsQueryResponse>
) : GetTransactionsEndpoint {

    override suspend fun getAll(id: String): GetTransactionsResponse {
        return  queryBus.execute(GetTransactionsQuery(id)).createResponseFromQueryResponse()
    }

    private fun GetTransactionsQueryResponse.createResponseFromQueryResponse(): GetTransactionsResponse {
        val transactions = this
            .transactions
            .map { dto -> TransactionResponse(dto.id, dto.amount, dto.description) }
            .toList()
        return GetTransactionsResponse(transactions)

    }
}