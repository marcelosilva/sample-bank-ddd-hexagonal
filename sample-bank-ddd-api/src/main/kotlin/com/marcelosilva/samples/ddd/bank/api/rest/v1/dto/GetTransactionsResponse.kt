package com.marcelosilva.samples.ddd.bank.api.rest.v1.dto

import java.math.BigDecimal

data class GetTransactionsResponse(
    val transactions: List<TransactionResponse>
)

data class TransactionResponse(
    val id: String,
    val amount: BigDecimal,
    val description: String?
)