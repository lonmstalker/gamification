package io.lonmstalker.gamification

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import reactivefeign.spring.config.EnableReactiveFeignClients

@EnableR2dbcAuditing
@SpringBootApplication
@EnableReactiveFeignClients
class GamificationImplApplication

fun main(args: Array<String>) {
    runApplication<GamificationImplApplication>(*args)
}
