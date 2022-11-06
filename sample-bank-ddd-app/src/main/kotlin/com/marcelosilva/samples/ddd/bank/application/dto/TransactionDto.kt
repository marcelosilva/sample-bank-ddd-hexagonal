package com.marcelosilva.samples.ddd.bank.application.dto

import java.math.BigDecimal

data class TransactionDto(
    val id: String,
    val amount: BigDecimal,
    val description: String? = null
)