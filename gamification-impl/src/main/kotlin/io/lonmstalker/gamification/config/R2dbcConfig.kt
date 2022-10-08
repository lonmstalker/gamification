package io.lonmstalker.gamification.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.lonmstalker.gamification.converter.JsonToNodeConverter
import io.lonmstalker.gamification.converter.NodeToJsonConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.dialect.PostgresDialect

@Configuration
class R2dbcConfig {

    @Bean
    fun customConversions(objectMapper: ObjectMapper): R2dbcCustomConversions? {
        val converters: MutableList<Converter<*, *>> = ArrayList()
        converters.add(NodeToJsonConverter(objectMapper))
        converters.add(JsonToNodeConverter(objectMapper))
        return R2dbcCustomConversions.of(PostgresDialect.INSTANCE, converters)
    }
}