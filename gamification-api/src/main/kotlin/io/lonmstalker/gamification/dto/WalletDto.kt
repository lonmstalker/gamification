package io.lonmstalker.gamification.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import io.lonmstalker.gamification.constants.Views
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class WalletDto @JsonCreator constructor(

    @field:Schema(description = "Id кошелька")
    val walletId: UUID?,

    @field:Schema(description = "Имя пользователя")
    val firstName: String,

    @field:Schema(description = "Фамилия пользователя")
    val lastName: String? = null,

    @field:Schema(description = "Отчество пользователя")
    val middleName: String? = null,

    @field:Schema(description = "Номер пользователя")
    val phoneNumber: String? = null,

    @field:Schema(description = "Почта пользователя")
    val email: String? = null,

    @field:JsonView(Views.INVISIBLE::class)
    @field:Schema(description = "Кол-во монет в MATIC")
    var maticAmount: Double = 0.0,

    @field:JsonView(Views.INVISIBLE::class)
    @field:Schema(description = "Кол-во монет в Coins")
    var coinsAmount: Double = 0.0,

    @field:JsonView(Views.INVISIBLE::class)
    @field:Schema(description = "Список NFT пользователя")
    var nft: List<NftDto>? = null,

    @field:Schema(description = "Роль пользователя")
    val userRole: Role = Role.USER
)

enum class Role {
    ADMIN, MANAGER, HR, USER
}