package io.lonmstalker.gamificationdb.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("user_info")
data class UserInfo(

    @Id
    @field:Column("id")
    val userId: UUID?,

    @field:Column("money")
    val money: Long,

    @field:Column("role")
    val role: Role = Role.USER
)
