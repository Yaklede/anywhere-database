package com.github.yaklede.anywhere.database.config.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeSerializer : JsonSerializer<LocalDateTime>() {
    companion object {
        private val DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    }

    override fun serialize(p0: LocalDateTime?, p1: JsonGenerator?, p2: SerializerProvider?) {
        p1?.writeString(p0?.format(DATE_TIME_FORMAT))
    }
}