package io.lonmstalker.gamificationdb.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("wallet")
data class Wallet(

    @Id
    @field:Column("id")
    val userId: UUID?,

    @field:Column("money")
    val money: Long = 0,

    @field:Column("nft")
    val nft: Long = 0,

    @field:Column("role")
    val userRole: Role = Role.USER
)

enum class Role {
    ADMIN, MANAGER, HR, USER
}
