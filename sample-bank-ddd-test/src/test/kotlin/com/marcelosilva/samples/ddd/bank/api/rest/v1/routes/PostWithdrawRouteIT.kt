package com.marcelosilva.samples.ddd.bank.api.rest.v1.routes

import com.marcelosilva.samples.ddd.bank.api.rest.v1.dto.WithdrawRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.reactive.server.WebTestClient
import java.math.BigDecimal
import java.util.UUID


@SpringBootTest
@AutoConfigureWebTestClient
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PostWithdrawRouteIT(
    @Autowired val route: PostWithdrawRoute
) {

    private fun defaultWithdrawRequest(
        amount: BigDecimal = BigDecimal.TEN
    ) = WithdrawRequest(
        amount = amount
    )

    @Test
    fun `Withdraw should throw error on invalid account id`() {
        val client = WebTestClient.bindToRouterFunction(route.withdrawRoute()).build()
        client
            .post()
            .uri("/v1/accounts/1234/withdraw")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(defaultWithdrawRequest())
            .exchange()
            .expectStatus()
            .is5xxServerError
    }

    @Test
    fun `Withdraw should withdraw with valid account id`() {
        val client = WebTestClient.bindToRouterFunction(route.withdrawRoute()).build()
        client
            .post()
            .uri("/v1/accounts/${UUID.randomUUID()}/withdraw")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(defaultWithdrawRequest())
            .exchange()
            .expectStatus()
            .isOk
    }

}