package com.github.yaklede.anywhere.database

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
    exclude = [
        DataSourceAutoConfiguration::class,
        R2dbcAutoConfiguration::class,
    ]
)
class AnywhereDatabaseApplication

fun main(args: Array<String>) {
    runApplication<AnywhereDatabaseApplication>(*args)
}
