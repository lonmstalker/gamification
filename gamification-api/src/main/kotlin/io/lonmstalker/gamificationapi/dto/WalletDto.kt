package io.lonmstalker.gamificationapi.dto

import com.fasterxml.jackson.annotation.JsonCreator
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

data class WalletDto @JsonCreator constructor(

    @field:Schema(description = "Id кошелька")
    val walletId: UUID?,

    @field:Schema(description = "Кол-во монет")
    val money: Long = 0,

    @field:Schema(description = "Кол-во nft")
    val nft: Long = 0,

    @field:Schema(description = "Роль пользователя")
    val userRole: Role = Role.USER
)

enum class Role {
    ADMIN, MANAGER, HR, USER
}