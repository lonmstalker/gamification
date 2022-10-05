package io.lonmstalker.gamificationapi.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonView
import io.lonmstalker.gamificationapi.constants.Views
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
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

    @JsonView(Views.Admin::class)
    @field:Schema(description = "Награда может меняться руководителем или HR")
    val canBeChangedReward: Boolean = false,

    @JsonView(Views.Admin::class)
    @field:Schema(description = "Когда создано")
    val createdDate: LocalDateTime?,

    @JsonView(Views.Admin::class)
    @field:Schema(description = "Кем создано")
    val createdBy: UUID?,

    @JsonView(Views.Admin::class)
    @field:Schema(description = "Когда обновлено")
    val updatedDate: LocalDateTime?,

    @JsonView(Views.Admin::class)
    @field:Schema(description = "Кем обновлено")
    val updatedBy: UUID?
)

enum class OperationType {
    PLUS, MINUS
}