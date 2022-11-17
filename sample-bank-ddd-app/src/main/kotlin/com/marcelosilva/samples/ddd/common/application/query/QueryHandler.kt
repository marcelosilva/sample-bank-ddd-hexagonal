package com.marcelosilva.samples.ddd.common.application.query

interface QueryHandler<T : Query, Z : QueryResponse> {
    suspend fun execute(query: T): Z
}

abstract class Query
abstract class QueryResponse