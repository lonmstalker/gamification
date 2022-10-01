package io.lonmstalker.gamificationdb.model

import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.UUID

@Table("operations")
data class Operation(

    @field:Id
    @field:Column("id")
    val operationId: UUID?,

    @field:Column("name")
    val name: String,

    @field:Column("description")
    val description: String?,

    @field:Column("money_reward")
    val moneyReward: Long?,

    @field:Column("nft_reward")
    val nftReward: UUID?,

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
