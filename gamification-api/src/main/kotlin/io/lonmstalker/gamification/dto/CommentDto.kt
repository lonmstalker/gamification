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
data class CommentDto(

    @field:Schema(description = "Id комментария")
    val commentId: UUID?,

    @field:Schema(description = "Id новости")
    val newsId: UUID?,

    @field:Schema(description = "Текст комментария")
    val text: String,

    @field:Schema(description = "Было ли исправление")
    val edited: Boolean,

    @field:Schema(description = "Кол-во благодарностей")
    val likes: Int = 0,

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
