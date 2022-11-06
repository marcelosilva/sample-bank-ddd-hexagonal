package com.marcelosilva.samples.ddd.common.application.command

interface CommandBus<T:Command> {
    suspend fun execute(command: T)
}

interface Command