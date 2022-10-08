package io.lonmstalker.gamification.model

import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("comments")
data class Comment(

    @field:Id
    @field:Column("comment_id")
    val commentId: UUID? = null,

    @field:Column("news_id")
    val newsId: UUID,

    @field:Column("text")
    val text: String,

    @field:Column("edited")
    val edited: Boolean,

    @field:Column("likes")
    val likes: Int? = null,

    @field:CreatedDate
    @field:Column("created_date")
    val createdDate: LocalDateTime? = null,

    @field:CreatedBy
    @field:Column("created_by")
    val createdBy: UUID? = null,

    @field:LastModifiedDate
    @field:Column("updated_date")
    val updatedDate: LocalDateTime? = null,

    @field:LastModifiedBy
    @field:Column("updated_by")
    val updatedBy: UUID?,

    @field:Version
    @field:Column("version")
    val version: Long = 0
)