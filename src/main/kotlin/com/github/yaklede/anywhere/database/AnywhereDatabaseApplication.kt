package com.github.yaklede.anywhere.database

import com.apple.eawt.Application
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AnywhereDatabaseApplication

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
