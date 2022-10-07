package io.lonmstalker.gamification.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class BalanceRpDto(
    @field:JsonProperty("maticAmount") private val maticAmount: Double,
    @field:JsonProperty("coinsAmount") private val coinsAmount: Double,
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class NftBalanceRpDto(
    @field:JsonProperty("balance") private val balance: List<NftDto>
) {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
    data class NftDto(
        @field:JsonProperty("URI") private val uri: String, // унифицированный (единообразный) идентификатор ресурса, сопряженный с NFT-коллекцией
        @field:JsonProperty("tokens") private val tokens: List<Int>, // - массив NFT. Т.е. 5,3,4,6 - уникальные идентификаторы отдельного NFT в NFT-коллекции
    )
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class GenerateBalanceRqDto(
    @field:JsonProperty("toPublicKey") private val maticAmount: Double,
    @field:JsonProperty("uri") private val coinsAmount: Double,
    @field:JsonProperty("nftCount") private val nftCount: Int
)