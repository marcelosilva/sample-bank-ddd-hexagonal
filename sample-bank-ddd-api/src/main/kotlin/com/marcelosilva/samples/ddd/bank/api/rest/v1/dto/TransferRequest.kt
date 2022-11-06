package com.marcelosilva.samples.ddd.bank.api.rest.v1.dto

import java.math.BigDecimal

data class TransferRequest(
    val destinationId: String,
    val amount: BigDecimal
)
