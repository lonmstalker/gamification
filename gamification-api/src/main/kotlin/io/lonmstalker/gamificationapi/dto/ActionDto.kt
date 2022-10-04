package io.lonmstalker.gamificationapi.dto

import com.fasterxml.jackson.annotation.JsonCreator
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.*

data class ActionDto @JsonCreator constructor(

    @field:Schema(description = "Id действия")
    val actionId: UUID?,

    @field:Schema(description = "Название действия")
    val name: String,

    @field:Schema(description = "Описание действия")
    val description: String?,

    @field:Schema(description = "Награда в монетах")
    val moneyReward: Long?,

    @field:Schema(description = "Награда в nft")
    val nftReward: UUID?,

    @field:Schema(description = "Тип операции")
    val operationType: OperationType,

    @field:Schema(description = "Награда может меняться руководителем или HR")
    val canBeChangedReward: Boolean = false,

    @field:Schema(description = "Когда создано")
    val createdDate: LocalDateTime?,

    @field:Schema(description = "Кем создано")
    val createdBy: UUID?,

    @field:Schema(description = "Когда обновлено")
    val updatedDate: LocalDateTime?,

    @field:Schema(description = "Кем обновлено")
    val updatedBy: UUID?
)

enum class OperationType {
    PLUS, MINUS
}