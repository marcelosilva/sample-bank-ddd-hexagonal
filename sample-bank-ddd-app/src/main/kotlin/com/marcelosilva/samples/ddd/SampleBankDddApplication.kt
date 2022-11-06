package com.marcelosilva.samples.ddd

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@OpenAPIDefinition(info = Info(title = "APIs", version = "1.0", description = "Documentation APIs v1.0"))
open class SampleBankDddApplication

fun main(args: Array<String>) {
    runApplication<SampleBankDddApplication>(*args)
}
