package io.lonmstalker.gamificationdb.model

import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.UUID

@Table("triggers")
data class Trigger(

    @field:Id
    @field:Column("id")
    val triggerId: UUID?,

    @field:Column("name")
    val name: String,

    @field:Column("user_id")
    val userId: UUID?,

    @field:Column("action_id")
    val actionId: UUID,

    @field:Column("group_id")
    val team: Team?,

    @field:Column("trigger_type")
    val triggerType: String,

    @field:Column("reward_time")
    val rewardTime: String,

    @field:CreatedDate
    @field:Column("created_date")
    val createdDate: LocalDateTime?,

    @field:CreatedBy
    @field:Column("created_by")
    val createdBy: UUID?,

    @field:LastModifiedDate
    @field:Column("updated_date")
    val updatedDate: LocalDateTime?,

    @field:LastModifiedBy
    @field:Column("updated_by")
    val updatedBy: UUID?
)