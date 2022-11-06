package com.marcelosilva.samples.ddd.bank.api.rest.v1.routes

import com.marcelosilva.samples.ddd.bank.api.rest.v1.dto.WithdrawRequest
import com.marcelosilva.samples.ddd.bank.api.rest.v1.dto.WithdrawResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.Explode
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.enums.ParameterStyle
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
open class PostWithdrawRoute(
    private val endpoint: WithdrawEndpoint
) {
    @Bean
    @RouterOperations(
        RouterOperation(
            path = "/v1/accounts/{id}/withdraw",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(APPLICATION_JSON_VALUE),
            operation = Operation(
                operationId = "transfer",
                method = "POST",
                parameters = [
                    Parameter(
                        name = "id",
                        `in` = ParameterIn.PATH,
                        style = ParameterStyle.SIMPLE,
                        explode = Explode.FALSE,
                        required = true
                    )
                ],
                requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Transfer Request", content = [
                        Content(
                            schema = Schema(implementation = WithdrawRequest::class)
                        )
                    ]
                )
            )
        )
    )
    open fun withdrawRoute() = coRouter {
        "/v1/accounts/{id}/withdraw".nest {
            POST("") { request ->
                ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(
                        endpoint.withdraw(
                            request.pathVariable("id"),
                            request.awaitBody(WithdrawRequest::class)
                        )
                    )
            }
        }
    }
}

interface WithdrawEndpoint {
    suspend fun withdraw(id: String, withdrawRequest: WithdrawRequest): WithdrawResponse
}