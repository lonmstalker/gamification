package io.lonmstalker.gamification.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class TransferRqDto(
    @field:JsonProperty("fromPrivateKey") private val fromPrivateKey: String,
    @field:JsonProperty("toPublicKey") private val toPublicKey: String,
    @field:JsonProperty("amount") private val amount: Double?,
    @field:JsonProperty("amount") private val tokenId: Int?
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class TransferRpDto(
    @field:JsonProperty("transactionHash") private val transactionHash: String
)

enum class TransferType(type: String) {
    MATIC("matic"), RUBLE("ruble"), NFT("nft")
}