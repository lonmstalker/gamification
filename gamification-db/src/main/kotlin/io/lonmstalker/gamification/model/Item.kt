package io.lonmstalker.gamification.model

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.UUID

@Table("items")
data class Item(

    @field:Id
    @field:Column("item_id")
    val itemId: UUID? = null,

    @field:Column("name")
    val name: String,

    @field:Column("description")
    val description: String,

    @field:Column("image_uri")
    val imageUri: String,

    @field:Column("coins")
    val coins: Double?,

    @field:Column("nft")
    val nft: Int?,

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
