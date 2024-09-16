package com.github.yaklede.anywhere.database.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.yaklede.anywhere.database.config.jackson.LocalDateSerializer
import com.github.yaklede.anywhere.database.config.jackson.LocalDateTimeSerializer
import com.github.yaklede.anywhere.database.config.jackson.LocalTimeSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Configuration
@EnableWebFlux
class WebConfig: WebFluxConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("GET", "POST" ,"PUT", "DELETE", "OPTIONS")
            .allowCredentials(true)
            .maxAge(3600)
    }

    @Bean
    @Primary
    fun objectMapper() = ObjectMapper().apply {
        registerKotlinModule()
        registerModule(JavaTimeModule())

        val localDateTimeModule = SimpleModule()

        localDateTimeModule.apply {
            addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
            addSerializer(LocalDate::class.java, LocalDateSerializer())
            addSerializer(LocalTime::class.java, LocalTimeSerializer())
        }

        registerModule(localDateTimeModule)

    }
}