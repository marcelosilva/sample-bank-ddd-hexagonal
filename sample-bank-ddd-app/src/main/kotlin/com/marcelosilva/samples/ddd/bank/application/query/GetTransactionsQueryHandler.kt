package com.marcelosilva.samples.ddd.bank.application.query

import com.marcelosilva.samples.ddd.bank.application.dto.TransactionDto
import com.marcelosilva.samples.ddd.common.application.query.QueryHandler
import com.marcelosilva.samples.ddd.common.application.query.QueryResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.UUID


@Component
class GetTransactionsQueryHandler : QueryHandler<GetTransactionsQuery, GetTransactionsQueryResponse> {

    override suspend fun execute(query: GetTransactionsQuery): GetTransactionsQueryResponse = coroutineScope {
        val response: Deferred<GetTransactionsQueryResponse> = async {
            GetTransactionsQueryResponse(
                listOf(
                    TransactionDto(UUID.randomUUID().toString(), BigDecimal("5000")),
                    TransactionDto(UUID.randomUUID().toString(), BigDecimal("4000")),
                    TransactionDto(UUID.randomUUID().toString(), BigDecimal("1000"))
                )
            )
        }
        response.await()
    }

    override fun executeSync(query: GetTransactionsQuery): GetTransactionsQueryResponse {
        return GetTransactionsQueryResponse(
            listOf(
                TransactionDto(UUID.randomUUID().toString(), BigDecimal("5000")),
                TransactionDto(UUID.randomUUID().toString(), BigDecimal("4000")),
                TransactionDto(UUID.randomUUID().toString(), BigDecimal("1000"))
            )
        )
    }
}

data class GetTransactionsQueryResponse(
    val transactions: List<TransactionDto>
) : QueryResponse()
