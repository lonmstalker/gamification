package io.lonmstalker.gamification.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class NftDto(
    @field:JsonProperty("uri") val uri: String, // унифицированный (единообразный) идентификатор ресурса, сопряженный с NFT-коллекцией
    @field:JsonProperty("tokens") val tokens: List<Int>, // - массив NFT. Т.е. 5,3,4,6 - уникальные идентификаторы отдельного NFT в NFT-коллекции
)