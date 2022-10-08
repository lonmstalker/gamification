package io.lonmstalker.gamification.converter

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.r2dbc.postgresql.codec.Json
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter

@ReadingConverter
class JsonToNodeConverter(private val objectMapper: ObjectMapper) : Converter<Json, JsonNode> {
    override fun convert(source: Json): JsonNode = this.objectMapper.readTree(source.asArray())
}