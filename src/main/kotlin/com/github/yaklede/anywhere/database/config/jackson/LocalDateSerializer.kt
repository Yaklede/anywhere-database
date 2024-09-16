package com.github.yaklede.anywhere.database.config.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateSerializer: JsonSerializer<LocalDate>() {
    companion object {
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }
    override fun serialize(p0: LocalDate?, p1: JsonGenerator?, p2: SerializerProvider?) {
        p1?.writeString(p0?.format(DATE_FORMAT))
    }
}