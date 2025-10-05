package com.greg.demoproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class DemoprojectApplication


fun main(args: Array<String>) {
	runApplication<DemoprojectApplication>(*args)
}
