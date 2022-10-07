package io.lonmstalker.gamification.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("wallet")
data class Wallet(

    @Id
    @field:Column("wallet_id")
    val userId: UUID?,

    @field:Column("role")
    val userRole: String,

    @field:Column("private_key")
    val privateKey: String,

    @field:Column("public_key")
    val publicKey: String
)