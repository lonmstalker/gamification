package io.lonmstalker.gamification.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class TransferRqDto @JsonCreator constructor(
    @param:JsonProperty("fromPrivateKey") val fromPrivateKey: String,
    @param:JsonProperty("toPublicKey") val toPublicKey: String,
    @param:JsonProperty("amount") val amount: Number? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class TransferRpDto @JsonCreator constructor(
    @param:JsonProperty("transaction") val transactionHash: String
)

enum class TransferType(val type: String) {
    MATIC("matic"), RUBLE("ruble"), NFT("nft")
}