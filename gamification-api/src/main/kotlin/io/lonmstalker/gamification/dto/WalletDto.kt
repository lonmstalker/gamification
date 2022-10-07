package io.lonmstalker.gamification.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class WalletDto @JsonCreator constructor(

    @field:Schema(description = "Id кошелька")
    val walletId: UUID?,

    @field:Schema(description = "Кол-во монет в MATIC")
    val maticAmount: Long = 0,

    @field:Schema(description = "Кол-во монет в Coins")
    val coinsAmount: Long = 0,

    @field:Schema(description = "Список NFT пользователя")
    val nftAmount: List<NftDto>,

    @field:Schema(description = "Роль пользователя")
    val userRole: Role = Role.USER
)

enum class Role {
    ADMIN, MANAGER, HR, USER
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class NftDto(
    @field:JsonProperty("uri")
    @field:Schema(description = "Идентификатор ресурса, сопряженный с NFT-коллекцией")
    private val uri: String,

    @field:JsonProperty("tokens")
    @field:Schema(description = "Уникальные идентификаторы отдельного NFT в NFT-коллекции")
    private val tokens: List<Int>
)