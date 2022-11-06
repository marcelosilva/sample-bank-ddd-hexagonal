package com.marcelosilva.samples.ddd.bank.application.query

import com.marcelosilva.samples.ddd.common.application.query.Query

class GetTransactionsQuery(val accountId: String) : Query()
