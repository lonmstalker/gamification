package io.lonmstalker.gamification.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonView
import io.lonmstalker.gamification.constants.Views
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class ItemDto @JsonCreator constructor(

    @field:Schema(description = "Id предмета")
    val itemId: UUID?,

    @field:Schema(description = "Название предмета")
    val name: String,

    @field:Schema(description = "Описание предмета")
    val description: String,

    @field:Schema(description = "Ссылка на картинку")
    val imageUri: String,

    @field:Schema(description = "Цена в монетах")
    val moneyPrice: Long,

    @field:Schema(description = "Цена в nft")
    val nftPrice: Long,

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
