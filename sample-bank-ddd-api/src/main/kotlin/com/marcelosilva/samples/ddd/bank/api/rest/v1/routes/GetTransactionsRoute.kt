package com.marcelosilva.samples.ddd.bank.api.rest.v1.routes

import com.marcelosilva.samples.ddd.bank.api.rest.v1.dto.GetTransactionsResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.Explode
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.enums.ParameterStyle
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
open class GetTransactionsRoute(
    private val endpoint: GetTransactionsEndpoint
) {
    @Bean
    @RouterOperations(
        RouterOperation(
            path = "/v1/accounts/{id}/transactions",
            method = arrayOf(RequestMethod.GET),
            operation = Operation(
                operationId = "getTransactions",
                method = "GET",
                parameters = [
                    Parameter(
                        name = "id",
                        `in` = ParameterIn.PATH,
                        style = ParameterStyle.SIMPLE,
                        explode = Explode.FALSE,
                        required = true
                    )]
            )
        )
    )
    open fun transactionsRoute() = coRouter {
        "/v1/accounts/{id}/transactions".nest {
            GET("") { request ->
                ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(endpoint.getAll(request.pathVariable("id")))
            }
        }
    }
}

interface GetTransactionsEndpoint {
    suspend fun getAll(id: String): GetTransactionsResponse
}