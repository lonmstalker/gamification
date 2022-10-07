package io.lonmstalker.gamification.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class NftInfoRpDto(
    @field:JsonProperty("tokenId") private val tokenId: Int, // идентификатор NFT
    @field:JsonProperty("uri") private val uri: Double, // унифицированный (единообразный) идентификатор ресурса
    @field:JsonProperty("publicKey") private val publicKey: Double,
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class TransactionInfoRpDto(
    @field:JsonProperty("wallet_id") private val walletId: String, // публичный ключ/адрес кошелька-владельца NFT-коллекции
    @field:JsonProperty("tokens") private val tokens: List<Int> // массив NFT идентификаторов сгенерированных в транзакции
)