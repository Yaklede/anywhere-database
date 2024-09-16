package com.github.yaklede.anywhere.database.config.jackson

import com.fasterxml.jackson.databind.JsonSerializer
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class LocalTimeSerializer : JsonSerializer<LocalTime>() {
    companion object {
        private val TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss")
    }

    override fun serialize(p0: LocalTime?, p1: com.fasterxml.jackson.core.JsonGenerator?, p2: com.fasterxml.jackson.databind.SerializerProvider?) {
        p1?.writeString(p0?.format(TIME_FORMAT))
    }
}