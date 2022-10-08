package io.lonmstalker.gamification.converter

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.r2dbc.postgresql.codec.Json
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter

@WritingConverter
class NodeToJsonConverter(private val objectMapper: ObjectMapper) : Converter<JsonNode, Json> {
    override fun convert(source: JsonNode): Json = Json.of(this.objectMapper.writeValueAsBytes(source))
}