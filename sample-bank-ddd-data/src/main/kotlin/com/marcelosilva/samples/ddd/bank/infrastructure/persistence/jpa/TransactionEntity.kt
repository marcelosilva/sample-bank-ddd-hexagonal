package com.marcelosilva.samples.ddd.bank.infrastructure.persistence.jpa

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.UUID

@Table("transaction")
open class TransactionEntity {
    @Id
    var id: UUID? = null
    var accountId: UUID? = null
    var amount: BigDecimal? = null
    var description: String? = null
}