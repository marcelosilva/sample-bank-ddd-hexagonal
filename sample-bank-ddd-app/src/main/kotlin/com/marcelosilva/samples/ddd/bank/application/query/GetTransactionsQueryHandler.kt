package com.marcelosilva.samples.ddd.bank.application.query

import com.marcelosilva.samples.ddd.bank.application.dto.TransactionDto
import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.bank.domain.service.TransactionService
import com.marcelosilva.samples.ddd.common.application.query.QueryHandler
import com.marcelosilva.samples.ddd.common.application.query.QueryResponse
import org.springframework.stereotype.Component
import java.util.UUID


@Component
open class GetTransactionsQueryHandler(
    private val transactionService: TransactionService
) : QueryHandler<GetTransactionsQuery, GetTransactionsQueryResponse> {

    override suspend fun execute(query: GetTransactionsQuery): GetTransactionsQueryResponse {
        val transactions = transactionService.findByAccountId(AccountId(UUID.fromString(query.accountId)))
        return GetTransactionsQueryResponse(
            transactions.map {
                TransactionDto(
                    id = it.id().id.toString(),
                    amount = it.amount().amount,
                    description = it.description().description
                )
            }
        )
    }
}

data class GetTransactionsQueryResponse(
    val transactions: List<TransactionDto>
) : QueryResponse()
