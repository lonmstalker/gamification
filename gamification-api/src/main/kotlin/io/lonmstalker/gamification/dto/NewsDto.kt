package io.lonmstalker.gamification.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonView
import io.lonmstalker.gamification.constants.Views
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class NewsDto(

    @field:Schema(description = "Id новости")
    val newsId: UUID?,

    @field:Schema(description = "Название новости")
    val name: String,

    @field:Schema(description = "Текст новости")
    val text: String,

    @field:Schema(description = "Открыты ли комментарии")
    val openComments: Boolean = true,

    @field:Schema(description = "Ссылка на картинку")
    val imageUri: String? = null,

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
    val updatedBy: UUID?,

    @field:Schema(description = "Версия новости")
    val version: Long = 0,
)
