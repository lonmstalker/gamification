package io.lonmstalker.gamification.model

import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("news")
data class News (

    @field:Id
    @field:Column("news_id")
    val newsId: UUID? = null,

    @field:Column("name")
    val name: String,

    @field:Column("text")
    val text: String,

    @field:Column("open_comm")
    val openComments: Boolean,

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
    val updatedBy: UUID?,

    @field:Version
    @field:Column("version")
    val version: Long = 0
)