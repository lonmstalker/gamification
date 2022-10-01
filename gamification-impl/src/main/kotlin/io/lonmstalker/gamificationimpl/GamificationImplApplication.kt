package io.lonmstalker.gamificationimpl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing

@EnableR2dbcAuditing
@SpringBootApplication
class GamificationImplApplication

fun main(args: Array<String>) {
    runApplication<GamificationImplApplication>(*args)
}
