package io.lonmstalker.gamification.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("teams")
data class Team(

    @field:Id
    @field:Column("team_id")
    val teamId: UUID?,

    @field:Column("name")
    val name: String,

    @field:Column("team_type")
    val teamType: String
)