package io.lonmstalker.gamificationimpl

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(properties = ["spring.autoconfigure.exclude=" +
        "org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration"])
class GamificationImplApplicationTests {

    @Test
    fun contextLoads() {
    }

}
