package io.lonmstalker.gamificationapi.dto

import com.fasterxml.jackson.annotation.JsonCreator
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

data class TeamDto @JsonCreator constructor(

    @field:Schema(description = "Id команды")
    val teamId: UUID?,

    @field:Schema(description = "Название команды")
    val name: String,

    @field:Schema(description = "Тип команды")
    val teamType: TeamType
)

enum class TeamType {
    TEAM, TRIBE
}
