package io.lonmstalker.gamification.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class TriggerDto @JsonCreator constructor(

    @field:Schema(description = "Id триггера")
    val triggerId: UUID?,

    @field:Schema(description = "Название триггера")
    val name: String,

    @field:Schema(description = "Id пользователя")
    val userId: UUID?,

    @field:Schema(description = "Id действия")
    val actionId: UUID,

    @field:Schema(description = "Id команды")
    val teamId: UUID?,

    @field:Schema(description = "Тип триггера")
    val triggerType: TriggerType = TriggerType.ALL_USERS,

    @field:Schema(description = "Время старта")
    val rewardTime: String = "12:00",

    @field:Schema(description = "Когда создано")
    val createdDate: LocalDateTime?,

    @field:Schema(description = "Кем создано")
    val createdBy: UUID?,

    @field:Schema(description = "Когда обновлено")
    val updatedDate: LocalDateTime?,

    @field:Schema(description = "Кем обновлено")
    val updatedBy: UUID?
)

enum class TriggerType {
    ALL_USERS, USERS_GROUP, ROLE, ONE_USER
}