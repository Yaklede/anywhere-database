package com.github.yaklede.anywhere.database.dto

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactoryOptions
import org.springframework.r2dbc.core.DatabaseClient

data class ConnectInfo(
    val url: String,
    val port: Int,
    val username: String,
    val password: String,
    val driver: String,
) {
    fun getDatabaseClient(
    ): DatabaseClient {
        val connectionFactory = ConnectionFactories.get(
            ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, driver)
                .option(ConnectionFactoryOptions.HOST, url)
                .option(ConnectionFactoryOptions.PORT, port)
                .option(ConnectionFactoryOptions.USER, username)
                .option(ConnectionFactoryOptions.PASSWORD, password)
                .build()
        )

        return DatabaseClient.create(connectionFactory)
    }
}

data class ColumnInfo(val columnName: String, val dataType: String)

data class ExecuteQuery(
    val sql: String,
    val connectInfo: ConnectInfo,
)