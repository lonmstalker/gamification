package io.lonmstalker.gamificationapi.dto

import com.fasterxml.jackson.annotation.JsonCreator
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.*

data class ItemDto @JsonCreator constructor(

    @field:Schema(description = "Id предмета")
    val itemId: UUID?,

    @field:Schema(description = "Название предмета")
    val name: String,

    @field:Schema(description = "Описание предмета")
    val description: String,

    @field:Schema(description = "Цена в монетах")
    val moneyPrice: Long,

    @field:Schema(description = "Цена в nft")
    val nftPrice: Long,

    @field:Schema(description = "Когда создано")
    val createdDate: LocalDateTime?,

    @field:Schema(description = "Кем создано")
    val createdBy: UUID?,

    @field:Schema(description = "Когда обновлено")
    val updatedDate: LocalDateTime?,

    @field:Schema(description = "Кем обновлено")
    val updatedBy: UUID?
)
