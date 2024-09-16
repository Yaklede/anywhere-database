package com.github.yaklede.anywhere.database.service

import com.github.yaklede.anywhere.database.dto.ColumnInfo
import com.github.yaklede.anywhere.database.dto.ConnectInfo
import com.github.yaklede.anywhere.database.dto.ExecuteQuery
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class DatabaseService {

    suspend fun getDatabaseSchema(connectInfo: ConnectInfo): Map<String, Map<String, List<ColumnInfo>>> {
        val client = connectInfo.getDatabaseClient()
        // 스키마, 테이블, 컬럼 정보 쿼리 실행
        return client.sql("""
        SELECT 
            table_schema, 
            table_name, 
            column_name, 
            data_type 
        FROM information_schema.columns
        ORDER BY table_schema, table_name, ordinal_position
    """)
            .map { row ->
                val schema = row.get("table_schema", String::class.java)!!
                val table = row.get("table_name", String::class.java)!!
                val column = row.get("column_name", String::class.java)!!
                val dataType = row.get("data_type", String::class.java)!!
                Triple(schema, table, ColumnInfo(column, dataType))
            }
            .all()
            .collectList()
            .awaitFirst()
            .groupBy({ it.first }, { it.second to it.third }) // 스키마별로 그룹화
            .mapValues { (_, tableColumnPairs) ->
                tableColumnPairs.groupBy({ it.first }, { it.second }) // 테이블별로 그룹화
            }
    }

    suspend fun executeQuery(
        request: ExecuteQuery
    ): List<Map<String, Any>> {
        // 클라이언트에서 받은 쿼리를 실행
        val client = request.connectInfo.getDatabaseClient()

        // 날짜 및 시간 포맷터 정의
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        return client.sql(request.sql)
            .fetch()
            .all()
            .collectList()
            .awaitFirst()
            .map { row ->
                row.mapValues { (_, value) ->
                    when (value) {
                        is LocalDateTime -> value.format(formatter)
                        else -> value
                    }
                }
            }
    }
}
