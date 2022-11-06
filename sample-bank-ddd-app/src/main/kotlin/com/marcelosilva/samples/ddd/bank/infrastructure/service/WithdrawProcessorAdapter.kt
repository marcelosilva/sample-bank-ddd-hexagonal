package com.marcelosilva.samples.ddd.bank.infrastructure.service

import com.marcelosilva.samples.ddd.bank.domain.model.aggregate.AccountId
import com.marcelosilva.samples.ddd.bank.domain.processor.WithdrawProcessor
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class WithdrawProcessorAdapter: WithdrawProcessor  {
    private var log = LoggerFactory.getLogger(WithdrawProcessorAdapter::class.java)

    override fun withdraw(accountId: AccountId, amount: BigDecimal) {
        log.info("Processing withdraw of account ${accountId.id} with amount $amount")
    }
}