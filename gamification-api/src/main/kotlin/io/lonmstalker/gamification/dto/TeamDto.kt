package io.lonmstalker.gamification.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY)
data class TeamDto @JsonCreator constructor(

    @field:Schema(description = "Id команды")
    val teamId: UUID?,

    @field:Schema(description = "Название команды")
    val name: String,

    @field:Schema(description = "Тип команды")
    val teamType: TeamType
)

enum class TeamType {
    TEAM, TRIBE, COMPANY
}
