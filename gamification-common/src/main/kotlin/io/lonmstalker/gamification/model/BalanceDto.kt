package io.lonmstalker.gamification.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.lonmstalker.gamification.dto.NftDto

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class BalanceRpDto(
    @field:JsonProperty("maticAmount") val maticAmount: Double,
    @field:JsonProperty("coinsAmount") val coinsAmount: Double,
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class NftBalanceRpDto(
    @field:JsonProperty("balance") val balance: List<NftDto>
)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class GenerateBalanceRqDto(
    @field:JsonProperty("toPublicKey") val publicKey: String,
    @field:JsonProperty("uri") val uri: String,
    @field:JsonProperty("nftCount") val nftCount: Int
)