package com.marcelosilva.samples.ddd.bank.infrastructure.persistence.jpa

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.UUID

@Table("account_transaction")
open class TransactionEntity(
    @Id
    @Column("id")
    open var transactionId: UUID? = null,
    open var accountId: UUID? = null,
    open var amount: BigDecimal? = null,
    open var description: String? = null,
) : Persistable<UUID> {
    override fun getId(): UUID {
        return transactionId!!
    }

    override fun isNew(): Boolean {
        return true
    }

}