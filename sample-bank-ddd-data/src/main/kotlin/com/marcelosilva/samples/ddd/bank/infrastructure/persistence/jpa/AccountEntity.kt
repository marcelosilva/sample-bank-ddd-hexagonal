package com.marcelosilva.samples.ddd.bank.infrastructure.persistence.jpa

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.UUID

@Table("account")
open class AccountEntity(
    @Id
    open var id: UUID? = null,
    open var balance: BigDecimal? = null
)