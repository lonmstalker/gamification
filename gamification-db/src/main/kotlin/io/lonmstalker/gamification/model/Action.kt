package io.lonmstalker.gamification.model

import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.UUID

@Table("actions")
data class Action(

    @field:Id
    @field:Column("action_id")
    val actionId: UUID?,

    @field:Column("name")
    val name: String,

    @field:Column("one_time")
    val oneTime: Boolean = false,

    @field:Column("description")
    val description: String?,

    @field:Column("coins")
    val coins: Double?,

    @field:Column("nft")
    val nft: Int?,

    @field:Column("operation_type")
    val operationType: String,

    @field:Column("can_be_changed_reward")
    val canBeChangedReward: Boolean = false,

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