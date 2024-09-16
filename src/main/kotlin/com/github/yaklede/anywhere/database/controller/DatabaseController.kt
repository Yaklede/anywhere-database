package com.github.yaklede.anywhere.database.controller

import com.github.yaklede.anywhere.database.dto.ConnectInfo
import com.github.yaklede.anywhere.database.dto.ExecuteQuery
import com.github.yaklede.anywhere.database.service.DatabaseService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/databases")
class DatabaseController(
    private val databaseService: DatabaseService
) {

    @PostMapping("/connect")
    suspend fun connect(@RequestBody request: ConnectInfo) = databaseService.getDatabaseSchema(request)

    @PostMapping("/query")
    suspend fun query(
        @RequestBody request: ExecuteQuery,
    ) = databaseService.executeQuery(request)
}